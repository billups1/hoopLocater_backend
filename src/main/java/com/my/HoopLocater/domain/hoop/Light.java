package com.my.HoopLocater.domain.hoop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum Light {

    NO_INFO("-(정보없음)", 0),
    PM9("오후 9시까지", 1),
    PM10("오후 10시까지", 2),
    PM11("오후 11시까지", 3),
    PM12("오후 12시까지", 4),
    NO_LIGHT("조명없음", 5)
    ;


    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
