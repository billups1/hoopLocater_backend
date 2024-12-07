package com.my.HoopLocater.infrastructure.web.auth.dto;

import com.my.HoopLocater.application.auth.command.AuthWithdrawalCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record AuthWithdrawalRequest(
        @Schema(description = "비밀번호", example = "zxc123##")
        @NotEmpty
        String password

) {
    public AuthWithdrawalCommand toCommand(UserDto userDto) {
        return AuthWithdrawalCommand.of(
                password,
                userDto
        );
    }

}