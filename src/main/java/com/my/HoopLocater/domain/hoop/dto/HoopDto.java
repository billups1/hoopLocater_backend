package com.my.HoopLocater.domain.hoop.dto;

import com.my.HoopLocater.domain.hoop.*;
import com.querydsl.core.annotations.QueryProjection;

public record HoopDto(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Integer hoopCount,
        FloorType floorType,
        Light light,
        FreeState freeState,
        StandardState standardState,
        Integer commentCount,
        String lastChangeUser,
        Integer likeCount,
        Boolean likeState

) {
    public static HoopDto from(Hoop hoop, Boolean likeState) {
        return new HoopDto(
                hoop.getId(),
                hoop.getName(),
                hoop.getLatitude(),
                hoop.getLongitude(),
                hoop.getHoopCount(),
                hoop.getFloorType(),
                hoop.getLight(),
                hoop.getFreeState(),
                hoop.getStandardState(),
                hoop.getCommentCount(),
                hoop.getLastChangeUser(),
                hoop.getLikeCount(),
                likeState
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
                hoop.getLight(),
                hoop.getFreeState(),
                hoop.getStandardState(),
                hoop.getCommentCount(),
                hoop.getLastChangeUser(),
                hoop.getLikeCount(),
                null
        );
    }
}
