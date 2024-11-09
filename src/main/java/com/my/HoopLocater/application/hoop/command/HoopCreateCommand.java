package com.my.HoopLocater.application.hoop.command;

import com.my.HoopLocater.domain.hoop.*;
import lombok.Getter;

@Getter
public class HoopCreateCommand {

    private String name;
    private Double latitude;
    private Double longitude;
    private Integer hoopCount;
    private String floorType;
    private String light;
    private String freeState;
    private String standardState;
    private String loginId;

    public HoopCreateCommand(String name,
                             Double latitude,
                             Double longitude,
                             Integer hoopCount,
                             String floorType,
                             String light,
                             String freeState,
                             String standardState,
                             String loginId) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.loginId = loginId;
    }

    public static HoopCreateCommand of(String name,
                                       Double latitude,
                                       Double longitude,
                                       Integer hoopCount,
                                       String floorType,
                                       String light,
                                       String freeState,
                                       String standardState,
                                       String loginId) {
        return new HoopCreateCommand(name, latitude, longitude, hoopCount, floorType, light, freeState, standardState, loginId);
    }

    public Hoop create() {
        return Hoop.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .hoopCount(hoopCount)
                .floorType(FloorType.valueOf(floorType))
                .light(Light.valueOf(light))
                .freeState(FreeState.valueOf(freeState))
                .standardState(StandardState.valueOf(standardState))
                .lastChangeUser(loginId)
                .hoopCount(0)
                .build();
    }
}
