package com.my.HoopLocater.domain.comment.dto;

import com.my.HoopLocater.domain.comment.Comment;
import com.querydsl.core.annotations.QueryProjection;

import java.time.format.DateTimeFormatter;

public record CommentDto(
        Long id,
        String writer,
        Long hoopId,
        String content,
        String writeDate

) {
    public static CommentDto from(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getWriter(),
                comment.getHoop().getId(),
                comment.getContent(),
                comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }

    @QueryProjection
    public CommentDto(Comment comment) {
        this(
                comment.getId(),
                comment.getWriter(),
                comment.getHoop().getId(),
                comment.getContent(),
                comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }

}
