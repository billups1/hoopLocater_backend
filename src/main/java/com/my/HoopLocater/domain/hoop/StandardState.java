package com.my.HoopLocater.domain.hoop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum StandardState {

    NO_INFO("(정보없음)", 0),
    STANDARD("정규코트", 1),
    UN_STANDARD("정규코트 아님", 2),

    ;


    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
