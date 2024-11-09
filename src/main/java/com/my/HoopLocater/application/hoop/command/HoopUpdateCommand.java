package com.my.HoopLocater.application.hoop.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
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
    private String anonymousId;
    private UserDto userDto;

    public HoopUpdateCommand(Long id,
                             String name,
                             Integer hoopCount,
                             String floorType,
                             String light,
                             String freeState,
                             String standardState,
                             String anonymousId,
                             UserDto userDto) {
        this.id = id;
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static HoopUpdateCommand of(Long id,
                                       String name,
                                       Integer hoopCount,
                                       String floorType,
                                       String light,
                                       String freeState,
                                       String standardState,
                                       String anonymousId,
                                       UserDto userDto) {
        return new HoopUpdateCommand(id, name, hoopCount, floorType, light, freeState, standardState, anonymousId, userDto);
    }

}
