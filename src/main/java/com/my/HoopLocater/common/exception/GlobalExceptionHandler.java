package com.my.HoopLocater.common.exception;

import com.my.HoopLocater.infrastructure.web.common.ApiResponse;
import com.my.HoopLocater.infrastructure.web.common.ApiResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    protected ApiResponse handler(Throwable throwable) {
        return ApiResponse.builder()
                .code(ApiResponseCode.FAIL.getCode())
                .data(throwable.getMessage())
                .message(ApiResponseCode.FAIL.getMessage())
                .build();
    }

}
