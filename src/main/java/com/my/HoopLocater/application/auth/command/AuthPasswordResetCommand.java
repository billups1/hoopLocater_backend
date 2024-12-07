package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthPasswordResetCommand {

    private String currentPassword;
    private String newPassword;
    private String newPasswordRecheck;
    private UserDto userDto;

    public AuthPasswordResetCommand(String currentPassword, String newPassword, String newPasswordRecheck, UserDto userDto) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordRecheck = newPasswordRecheck;
        this.userDto = userDto;
    }

    public static AuthPasswordResetCommand of(String currentPassword, String newPassword, String newPasswordRecheck, UserDto userDto) {
        return new AuthPasswordResetCommand(currentPassword, newPassword, newPasswordRecheck, userDto);
    }

}
