package com.my.HoopLocater.infrastructure.web.comment.dto;

import com.my.HoopLocater.application.comment.command.CommentCreateCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CommentCreateRequest(
        @Schema(description = "농구장 id", example = "1")
        @NotEmpty
        Long hoopId,
        @Schema(description = "내용", example = "댓글입니다.")
        @NotEmpty
        String content

) {
    public CommentCreateCommand toCommand(String anonymousId, UserDto userDto) {
        return CommentCreateCommand.of(
                hoopId,
                content,
                anonymousId,
                userDto
        );
    }

}