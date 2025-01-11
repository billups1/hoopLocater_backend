package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthProfileSettingCommand {

    private String nickName;
    private UserDto userDto;

    public AuthProfileSettingCommand(String nickName, UserDto userDto) {
        this.nickName = nickName;
        this.userDto = userDto;
    }

    public static AuthProfileSettingCommand of(String nickName, UserDto userDto) {
        return new AuthProfileSettingCommand(nickName, userDto);
    }

}
