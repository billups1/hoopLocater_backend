package com.my.HoopLocater.common.log;

import com.my.HoopLocater.configuration.auth.TokenProvider;
import com.my.HoopLocater.domain.apiLog.ApiLog;
import com.my.HoopLocater.infrastructure.persistence.apiLog.ApiLogJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogUtil {

    private final ApiLogJpaRepository apiLogJpaRepository;
    private final TokenProvider tokenProvider;

    public void insertLog() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        String accessToken = httpServletRequest.getHeader("accessToken");

        apiLogJpaRepository.save(
                ApiLog.builder()
                        .requestUri(httpServletRequest.getRequestURI())
                        .requestMethod(httpServletRequest.getMethod())
                        .clientIp(httpServletRequest.getHeader("X-Forwarded-For") != null ? httpServletRequest.getHeader("X-Forwarded-For") : httpServletRequest.getRemoteAddr())
                        .anonymousId(httpServletRequest.getHeader("anonymousId"))
                        .loginId(tokenProvider.validateToken(accessToken) ? tokenProvider.parseClaims(accessToken).getSubject() : null)
                        .responseTime(LocalDateTime.now())
                        .responseStatus(Integer.toString(httpServletResponse.getStatus()))
                        .build()
        );
    }

    public void insertExceptionLog(Throwable throwable) {
        String errClass;
        try {
            errClass = Class.forName(throwable.getStackTrace()[1].getClassName()).getSimpleName();
        } catch (ClassNotFoundException e) {
            errClass = "errClass : ClassNotFoundException e";
        }

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        String accessToken = httpServletRequest.getHeader("accessToken");

        apiLogJpaRepository.save(
                ApiLog.builder()
                        .requestUri(httpServletRequest.getRequestURI())
                        .requestMethod(httpServletRequest.getMethod())
                        .clientIp(httpServletRequest.getHeader("X-Forwarded-For") != null ? httpServletRequest.getHeader("X-Forwarded-For") : httpServletRequest.getRemoteAddr())
                        .anonymousId(httpServletRequest.getHeader("anonymousId"))
                        .loginId(tokenProvider.validateToken(accessToken) ? tokenProvider.parseClaims(accessToken).getSubject() : null)
                        .responseTime(LocalDateTime.now())
                        .responseStatus(Integer.toString(httpServletResponse.getStatus()))
                        .errClass(errClass)
                        .errMethod(throwable.getStackTrace()[1].getMethodName())
                        .errMessage(throwable.getMessage())
                        .build()
        );
    }
}
