package com.my.HoopLocater.infrastructure.web.comment.dto;

import com.my.HoopLocater.application.comment.command.CommentCreateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CommentCreateRequest(
        @Schema(description = "작성자", example = "aaaaaaaa")
        String writer,
        @Schema(description = "농구장 id", example = "1")
        @NotEmpty
        Long hoopId,
        @Schema(description = "내용", example = "댓글입니다.")
        @NotEmpty
        String content

) {
    public CommentCreateCommand toCommand() {
        return CommentCreateCommand.of(
                writer,
                hoopId,
                content
        );
    }

}