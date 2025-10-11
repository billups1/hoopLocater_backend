package com.my.HoopLocater.application.app;

import com.my.HoopLocater.application.app.command.AppStartCheckCommand;
import com.my.HoopLocater.application.app.command.AppUpdateRequiredCheckCommand;
import com.my.HoopLocater.domain.app.AppUpdateRequiredState;
import com.my.HoopLocater.infrastructure.persistence.app.AppVersionJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.blacklist.BlacklistJpaRepository;
import com.my.HoopLocater.infrastructure.web.dto.AppStartCheckResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Component
public class AppVersionCommandHandler {

    private final AppVersionJpaRepository appVersionJpaRepository;
    private final BlacklistJpaRepository blacklistJpaRepository;

    @Transactional
    public Boolean handler(AppUpdateRequiredCheckCommand command) {
        var appVersion = appVersionJpaRepository.findTop1ByAppUpdateRequiredStateOrderByVersionCodeDesc(AppUpdateRequiredState.UPDATE_REQUIRED); // 필수 업데이트 기준 버전
        if (command.getVersionCode() < appVersion.getVersionCode()) {
            return true;
        } else {
            return false;
        }
    }

    public AppStartCheckResponseDto handler(AppStartCheckCommand command) {
        // 앱버전 체크
        var appVersion = appVersionJpaRepository.findTop1ByAppUpdateRequiredStateOrderByVersionCodeDesc(AppUpdateRequiredState.UPDATE_REQUIRED); // 필수 업데이트 기준 버전
        Boolean updateRequiredCheck;
        if (command.getVersionCode() < appVersion.getVersionCode()) {
            updateRequiredCheck = true;
        } else {
            updateRequiredCheck = false;
        }

        // 블랙리스트 체크
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For") != null ? httpServletRequest.getHeader("X-Forwarded-For") : httpServletRequest.getRemoteAddr();
        Boolean blacklistState = blacklistJpaRepository.existsByIpOrAnonymousId(clientIp, command.getAnonymousId());

        return AppStartCheckResponseDto.from(updateRequiredCheck, blacklistState);

    }
}
