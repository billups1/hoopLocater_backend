package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthGetLoginTokenCommand {

    private UserDto userDto;

    public AuthGetLoginTokenCommand(UserDto userDto) {
        this.userDto = userDto;
    }

    public static AuthGetLoginTokenCommand of(UserDto userDto) {
        return new AuthGetLoginTokenCommand(userDto);
    }

}
