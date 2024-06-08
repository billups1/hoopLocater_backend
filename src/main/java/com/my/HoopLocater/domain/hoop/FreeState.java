package com.my.HoopLocater.domain.hoop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum FreeState {

    NO_INFO("(정보없음)", 0),
    FREE("무료", 1),
    PAID("유료", 2)
    ;


    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
