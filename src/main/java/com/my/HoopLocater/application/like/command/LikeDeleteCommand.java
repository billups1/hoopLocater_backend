package com.my.HoopLocater.application.like.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.like.Like;
import lombok.Getter;

@Getter
public class LikeDeleteCommand {

    private Long hoopId;
    private String anonymousId;
    private UserDto userDto;

    public LikeDeleteCommand(Long hoopId,
                             String anonymousId,
                             UserDto userDto) {
        this.hoopId = hoopId;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static LikeDeleteCommand of(Long hoopId,
                                       String anonymousId,
                                       UserDto userDto) {
        return new LikeDeleteCommand(hoopId, anonymousId, userDto);
    }

}
