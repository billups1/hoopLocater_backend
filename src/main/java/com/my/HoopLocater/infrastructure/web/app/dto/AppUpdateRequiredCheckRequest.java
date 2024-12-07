package com.my.HoopLocater.infrastructure.web.app.dto;

import com.my.HoopLocater.application.app.command.AppUpdateRequiredCheckCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AppUpdateRequiredCheckRequest(
        @Schema(description = "versionCode", example = "14")
        Integer versionCode,
        @Schema(description = "versionName", example = "1.1.0")
        @NotEmpty
        String versionName

) {
    public AppUpdateRequiredCheckCommand toCommand() {
        return AppUpdateRequiredCheckCommand.of(
                versionCode,
                versionName
        );
    }

}