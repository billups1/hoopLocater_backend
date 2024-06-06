package com.my.HoopLocater.application.hoop.command;

import com.my.HoopLocater.domain.hoop.FloorType;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.Light;
import lombok.Getter;

@Getter
public class HoopCreateCommand {

    private String name;
    private Double latitude;
    private Double longitude;
    private Integer hoopCount;
    private String floorType;
    private String light;

    public HoopCreateCommand(String name,
                             Double latitude,
                             Double longitude,
                             Integer hoopCount,
                             String floorType,
                             String light) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
    }

    public static HoopCreateCommand of(String name,
                                       Double latitude,
                                       Double longitude,
                                       Integer hoopCount,
                                       String floorType,
                                       String light) {
        return new HoopCreateCommand(name, latitude, longitude, hoopCount, floorType, light);
    }

    public Hoop create() {
        return Hoop.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .hoopCount(hoopCount)
                .floorType(FloorType.valueOf(floorType))
                .light(Light.valueOf(light))
                .build();
    }
}
