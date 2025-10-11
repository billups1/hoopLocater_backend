package com.my.HoopLocater.domain.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum BlacklistType {

    IP("IP", 0),
    ANONYMOUS_ID("anonymousId", 1);

    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
