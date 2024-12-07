package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthWithdrawalCommand {

    private String password;
    private UserDto userDto;

    public AuthWithdrawalCommand(String password, UserDto userDto) {
        this.password = password;
        this.userDto = userDto;
    }

    public static AuthWithdrawalCommand of(String password, UserDto userDto) {
        return new AuthWithdrawalCommand(password, userDto);
    }

}
