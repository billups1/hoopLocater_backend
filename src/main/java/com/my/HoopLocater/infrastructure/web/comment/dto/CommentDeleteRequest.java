package com.my.HoopLocater.infrastructure.web.comment.dto;

import com.my.HoopLocater.application.comment.command.CommentDeleteCommand;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CommentDeleteRequest(

        @Schema(description = "댓글 id", example = "1")
        @NotEmpty
        Long id,
        @Schema(description = "작성자", example = "aaaaaaaa")
        String writer


) {
    public CommentDeleteCommand toCommand(UserDto userDto) {
        return CommentDeleteCommand.of(
                id,
                writer,
                userDto
        );
    }

}