package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthLogoutCommand {

    private UserDto userDto;

    public AuthLogoutCommand(UserDto userDto) {
        this.userDto = userDto;
    }

    public static AuthLogoutCommand of(UserDto userDto) {
        return new AuthLogoutCommand(userDto);
    }

}
