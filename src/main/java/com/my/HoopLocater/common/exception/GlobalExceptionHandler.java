package com.my.HoopLocater.common.exception;

import com.my.HoopLocater.infrastructure.web.common.ApiResponseCode;
import com.my.HoopLocater.infrastructure.web.common.ApiResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    protected ApiResponseDto handler(Throwable throwable) {
        return ApiResponseDto.builder()
                .code(ApiResponseCode.FAIL.getCode())
                .data(throwable.getMessage())
                .message(ApiResponseCode.FAIL.getMessage())
                .build();
    }

}
