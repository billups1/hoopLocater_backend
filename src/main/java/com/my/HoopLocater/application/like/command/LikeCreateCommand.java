package com.my.HoopLocater.application.like.command;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.*;
import com.my.HoopLocater.domain.like.Like;
import lombok.Getter;

@Getter
public class LikeCreateCommand {

    private Long hoopId;
    private String anonymousId;
    private UserDto userDto;

    public LikeCreateCommand(Long hoopId,
                             String anonymousId,
                             UserDto userDto) {
        this.hoopId = hoopId;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static LikeCreateCommand of(Long hoopId,
                                       String anonymousId,
                                       UserDto userDto) {
        return new LikeCreateCommand(hoopId, anonymousId, userDto);
    }

    public Like create() {
        return Like.builder()
                .hoop(Hoop.builder().id(hoopId).build())
                .user(userDto != null ? User.builder().id(userDto.id()).build() : null)
                .anonymousId(userDto != null ? null : anonymousId)
                .build();
    }
}
