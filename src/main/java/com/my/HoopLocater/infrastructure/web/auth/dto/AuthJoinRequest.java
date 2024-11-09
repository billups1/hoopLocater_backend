package com.my.HoopLocater.infrastructure.web.auth.dto;

import com.my.HoopLocater.application.auth.command.AuthJoinCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthJoinRequest(
        @Schema(description = "로그인 ID", example = "example1234")
        String loginId,
        @Schema(description = "비밀번호", example = "zxc123##")
        @NotEmpty
        String password,
        @Schema(description = "비밀번호 재확인", example = "zxc123##")
        @NotEmpty
        String passwordRecheck,
        @Schema(description = "닉네임", example = "별명123")
        @NotEmpty
        String nickName

) {
    public AuthJoinCommand toCommand() {
        return AuthJoinCommand.of(
                loginId,
                password,
                passwordRecheck,
                nickName
        );
    }

}