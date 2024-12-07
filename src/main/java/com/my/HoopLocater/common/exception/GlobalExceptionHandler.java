package com.my.HoopLocater.common.exception;

import com.my.HoopLocater.infrastructure.web.common.ApiResponseCode;
import com.my.HoopLocater.infrastructure.web.common.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 이메일 로그인 시, 아이디/비밀번호 오류 잡기
    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    protected ApiResponseDto loginHandle(Exception e) {
        return ApiResponseDto.builder()
                .code(ApiResponseCode.FAIL.getCode())
                .message("아이디 또는 비밀번호가 일치하지 않습니다.")
                .build();
    }

    @ExceptionHandler(Throwable.class)
    protected ApiResponseDto handler(Throwable throwable) {
        return ApiResponseDto.builder()
                .code(ApiResponseCode.FAIL.getCode())
                .message(throwable.getMessage())
                .build();
    }

}
