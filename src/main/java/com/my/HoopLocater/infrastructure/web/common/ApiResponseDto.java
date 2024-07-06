package com.my.HoopLocater.infrastructure.web.common;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponseDto<T> {

    private String code;
    private T data;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Builder
    public ApiResponseDto(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
