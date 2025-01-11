package com.my.HoopLocater.infrastructure.web.auth.dto;

import com.my.HoopLocater.application.auth.command.AuthProfileSettingCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthProfileSettingRequest(
        @Schema(description = "닉네임", example = "별명123")
        @NotEmpty
        String nickName

) {
    public AuthProfileSettingCommand toCommand(UserDto userDto) {
        return AuthProfileSettingCommand.of(
                nickName,
                userDto
        );
    }

}