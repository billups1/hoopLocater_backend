package com.my.HoopLocater.domain.hoop.dto;

import com.my.HoopLocater.domain.hoop.FloorType;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.Light;
import com.querydsl.core.annotations.QueryProjection;

public record HoopDto(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Integer hoopCount,
        FloorType floorType,
        Light light

) {
    public static HoopDto from(Hoop hoop) {
        return new HoopDto(
                hoop.getId(),
                hoop.getName(),
                hoop.getLatitude(),
                hoop.getLongitude(),
                hoop.getHoopCount(),
                hoop.getFloorType(),
                hoop.getLight()
        );
    }

    @QueryProjection
    public HoopDto(Hoop hoop) {
        this(
                hoop.getId(),
                hoop.getName(),
                hoop.getLatitude(),
                hoop.getLongitude(),
                hoop.getHoopCount(),
                hoop.getFloorType(),
                hoop.getLight()
        );
    }
}
