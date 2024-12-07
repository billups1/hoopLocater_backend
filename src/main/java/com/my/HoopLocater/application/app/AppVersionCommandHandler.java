package com.my.HoopLocater.application.app;

import com.my.HoopLocater.application.app.command.AppUpdateRequiredCheckCommand;
import com.my.HoopLocater.domain.app.AppUpdateRequiredState;
import com.my.HoopLocater.infrastructure.persistence.app.AppVersionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AppVersionCommandHandler {

    private final AppVersionJpaRepository appVersionJpaRepository;

    @Transactional
    public Boolean handler(AppUpdateRequiredCheckCommand command) {
        var appVersion = appVersionJpaRepository.findTop1ByAppUpdateRequiredStateOrderByVersionCodeDesc(AppUpdateRequiredState.UPDATE_REQUIRED); // 필수 업데이트 기준 버전
        if (command.getVersionCode() < appVersion.getVersionCode()) {
            return true;
        } else {
            return false;
        }
    }

}
