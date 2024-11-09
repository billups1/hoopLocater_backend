package com.my.HoopLocater.application.hoop.command;

import lombok.Getter;

@Getter
public class HoopUpdateCommand {

    private Long id;
    private String name;
    private Integer hoopCount;
    private String floorType;
    private String light;
    private String freeState;
    private String standardState;
    private String loginId;

    public HoopUpdateCommand(Long id,
                             String name,
                             Integer hoopCount,
                             String floorType,
                             String light,
                             String freeState,
                             String standardState,
                             String loginId) {
        this.id = id;
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.loginId = loginId;
    }

    public static HoopUpdateCommand of(Long id,
                                       String name,
                                       Integer hoopCount,
                                       String floorType,
                                       String light,
                                       String freeState,
                                       String standardState,
                                       String loginId) {
        return new HoopUpdateCommand(id, name, hoopCount, floorType, light, freeState, standardState, loginId);
    }

}
