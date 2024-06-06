package com.my.HoopLocater.application.hoop.command;

import lombok.Getter;

@Getter
public class HoopUpdateCommand {

    private Long id;
    private String name;
    private Integer hoopCount;
    private String floorType;
    private String light;

    public HoopUpdateCommand(Long id,
                             String name,
                             Integer hoopCount,
                             String floorType,
                             String light) {
        this.id = id;
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
    }

    public static HoopUpdateCommand of(Long id,
                                       String name,
                                       Integer hoopCount,
                                       String floorType,
                                       String light) {
        return new HoopUpdateCommand(id, name, hoopCount, floorType, light);
    }

}
