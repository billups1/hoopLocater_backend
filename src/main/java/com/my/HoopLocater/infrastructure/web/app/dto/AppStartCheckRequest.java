package com.my.HoopLocater.infrastructure.web.app.dto;

import com.my.HoopLocater.application.app.command.AppStartCheckCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AppStartCheckRequest(
        @Schema(description = "versionCode", example = "14")
        Integer versionCode,
        @Schema(description = "versionName", example = "1.1.0")
        @NotEmpty
        String versionName

) {

    public AppStartCheckCommand toCommand(String anonymousId, UserDto userDto) {
        return AppStartCheckCommand.of(
                versionCode,
                versionName,
                anonymousId,
                userDto
        );
    }
}