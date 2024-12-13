package com.my.HoopLocater.infrastructure.web.like.dto;

import com.my.HoopLocater.application.like.command.LikeDeleteCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record LikeDeleteRequest(
        @Schema(description = "농구장 id", example = "10")
        @NotEmpty
        Long hoopId

) {
    public LikeDeleteCommand toCommand(String anonymousId, UserDto userDto) {
        return LikeDeleteCommand.of(
                hoopId,
                anonymousId,
                userDto
        );
    }

}