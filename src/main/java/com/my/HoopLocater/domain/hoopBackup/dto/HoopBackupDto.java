package com.my.HoopLocater.domain.hoopBackup.dto;

import com.my.HoopLocater.domain.hoop.*;
import com.my.HoopLocater.domain.hoopBackup.HoopBackup;
import com.querydsl.core.annotations.QueryProjection;

import java.time.format.DateTimeFormatter;

public record HoopBackupDto(
        Long id,
        Long hoopId,
        String name,
        Double latitude,
        Double longitude,
        Integer hoopCount,
        FloorType floorType,
        Light light,
        FreeState freeState,
        StandardState standardState,
        String lastChangeUser,
        String updatedAt

) {

    @QueryProjection
    public HoopBackupDto(HoopBackup hoop) {
        this(
                hoop.getId(),
                hoop.getHoopId(),
                hoop.getName(),
                hoop.getLatitude(),
                hoop.getLongitude(),
                hoop.getHoopCount(),
                hoop.getFloorType(),
                hoop.getLight(),
                hoop.getFreeState(),
                hoop.getStandardState(),
                hoop.getUser() != null ? hoop.getUser().getNickName() : hoop.getAnonymousId(),
                hoop.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
