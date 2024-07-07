package com.my.HoopLocater.application.hoopBackup.command;

import com.my.HoopLocater.domain.hoop.*;
import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import com.my.HoopLocater.domain.hoopBackup.UpdateMethod;
import lombok.Getter;

@Getter
public class HoopBackupCreateCommand {

    private Long hoopId;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer hoopCount;
    private FloorType floorType;
    private Light light;
    private FreeState freeState;
    private StandardState standardState;
    private String lastChangeUser;
    private UpdateMethod updateMethod;

    public HoopBackupCreateCommand(Long hoopId,
                                   String name,
                                   Double latitude,
                                   Double longitude,
                                   Integer hoopCount,
                                   FloorType floorType,
                                   Light light,
                                   FreeState freeState,
                                   StandardState standardState,
                                   String lastChangeUser,
                                   UpdateMethod updateMethod) {
        this.hoopId = hoopId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.lastChangeUser = lastChangeUser;
        this.updateMethod = updateMethod;
    }

    public static HoopBackupCreateCommand of(Long hoopId,
                                             String name,
                                             Double latitude,
                                             Double longitude,
                                             Integer hoopCount,
                                             FloorType floorType,
                                             Light light,
                                             FreeState freeState,
                                             StandardState standardState,
                                             String lastChangeUser,
                                             UpdateMethod updateMethod) {
        return new HoopBackupCreateCommand(hoopId, name, latitude, longitude, hoopCount, floorType, light, freeState, standardState, lastChangeUser, updateMethod);
    }

    public static HoopBackupCreateCommand of(Hoop hoop, UpdateMethod updateMethod) {
        return new HoopBackupCreateCommand(hoop.getId(), hoop.getName(), hoop.getLatitude(), hoop.getLongitude(), hoop.getHoopCount(), hoop.getFloorType(), hoop.getLight(), hoop.getFreeState(), hoop.getStandardState(), hoop.getLastChangeUser(), updateMethod);
    }

    public HoopBackup create() {
        return HoopBackup.builder()
                .hoopId(hoopId)
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .hoopCount(hoopCount)
                .floorType(floorType)
                .light(light)
                .freeState(freeState)
                .standardState(standardState)
                .lastChangeUser(lastChangeUser)
                .updateMethod(updateMethod)
                .build();
    }
}
