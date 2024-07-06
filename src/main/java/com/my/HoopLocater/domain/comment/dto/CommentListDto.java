package com.my.HoopLocater.domain.comment.dto;

import com.my.HoopLocater.domain.comment.Comment;

public record CommentListDto(
        Long id,
        String writer,
        Long hoopId,
        String content,
        Boolean myCommentState

) {
    public static CommentListDto from(Comment comment, String loginUserId) {
        return new CommentListDto(
                comment.getId(),
                comment.getWriter(),
                comment.getHoop().getId(),
                comment.getContent(),
                comment.getWriter().equals(loginUserId)
        );
    }

}
