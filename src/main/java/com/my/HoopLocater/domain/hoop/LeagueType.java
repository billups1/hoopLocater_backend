package com.my.HoopLocater.domain.hoop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum LeagueType {

    HOOP("일반 농구장", 0),
    KBL("KBL", 1),
    WKBL("WKBL", 2);

    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
