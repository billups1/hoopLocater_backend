package com.my.HoopLocater.domain.hoopBackup;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum UpdateMethod {

    CREATE("최초생성", 0),
    UPDATE("수정", 1)
    ;


    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
