package com.my.HoopLocater.infrastructure.web.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum ApiResponseCode {

    SUCCESS("success", "성공"),
    FAIL("fail", "실패");

    private final String code;
    private final String message;

}
