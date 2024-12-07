package com.my.HoopLocater.infrastructure.web.auth.dto;

import com.my.HoopLocater.application.auth.command.AuthPasswordResetCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthPasswordResetRequest(
        @Schema(description = "현재 비밀번호", example = "zxc123##")
        @NotEmpty
        String currentPassword,
        @Schema(description = "새 비밀번호", example = "zxc123##new")
        @NotEmpty
        String newPassword,
        @Schema(description = "새 비밀번호 재확인", example = "zxc123##new")
        @NotEmpty
        String newPasswordRecheck

) {
    public AuthPasswordResetCommand toCommand(UserDto userDto) {
        return AuthPasswordResetCommand.of(
                currentPassword,
                newPassword,
                newPasswordRecheck,
                userDto
        );
    }

}