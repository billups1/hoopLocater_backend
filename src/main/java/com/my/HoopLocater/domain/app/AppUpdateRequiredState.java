package com.my.HoopLocater.domain.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum AppUpdateRequiredState {

    UPDATE_REQUIRED("업데이트 필수", 0),
    UPDATE_NOT_REQUIRED("업데이트 필수 아님", 1);

    final private String krName;
    final private Integer order;

    public String getKey() {
        return name();
    }

}
