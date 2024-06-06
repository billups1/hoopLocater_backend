package com.my.HoopLocater.domain.hoop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum FloorType {

    URETHANE("우레탄", 0),
    PARQUET("마루바닥", 1),
    ASPHALT("아스팔트", 2),
    DIRT("모래", 3),
    ETC("기타", 4);


    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
