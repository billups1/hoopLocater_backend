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
        String lastChangeUser,
        Integer commentCount,
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
                hoop.getUser() != null ? hoop.getUser().getNickName() : hoop.getAnonymousId(),
                hoop.getCommentCount(),
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
                hoop.getUser() != null ? hoop.getUser().getNickName() : hoop.getAnonymousId(),
                hoop.getCommentCount(),
                hoop.getLikeCount(),
                null
        );
    }
}
