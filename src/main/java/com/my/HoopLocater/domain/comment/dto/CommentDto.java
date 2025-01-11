package com.my.HoopLocater.domain.comment.dto;

import com.my.HoopLocater.domain.comment.Comment;
import com.querydsl.core.annotations.QueryProjection;

import java.time.format.DateTimeFormatter;

public record CommentDto(
        Long id,
        String writer,
        Long hoopId,
        String content,
        String writeDate,
        Boolean myCommentState

) {
    public static CommentDto from(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getUser() != null ? comment.getUser().getNickName() : comment.getAnonymousId(),
                comment.getHoop().getId(),
                comment.getContent(),
                comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE),
                null
        );
    }

    @QueryProjection
    public CommentDto(Comment comment, Boolean myCommentState) {
        this(
                comment.getId(),
                comment.getUser() != null ? comment.getUser().getNickName() : comment.getAnonymousId(),
                comment.getHoop().getId(),
                comment.getContent(),
                comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE),
                myCommentState
        );
    }

}
