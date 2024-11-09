package com.my.HoopLocater.infrastructure.web.auth.dto;

import com.my.HoopLocater.application.auth.command.AuthLoginCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthLoginRequest(
        @Schema(description = "로그인 ID", example = "example1234")
        String loginId,
        @Schema(description = "비밀번호", example = "zxc123##")
        @NotEmpty
        String password

) {
    public AuthLoginCommand toCommand() {
        return AuthLoginCommand.of(
                loginId,
                password
        );
    }

}