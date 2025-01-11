package com.my.HoopLocater.domain.comment.dto;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.Comment;

public record CommentListDto(
        Long id,
        String writer,
        Long hoopId,
        String content,
        Boolean myCommentState

) {
    public static CommentListDto from(Comment comment, UserDto userDto, String anonymousId) {
        return new CommentListDto(
                comment.getId(),
                comment.getUser() != null ? comment.getUser().getNickName() : comment.getAnonymousId(),
                comment.getHoop().getId(),
                comment.getContent(),
                userDto != null ? comment.getUser().getId() == userDto.id() : comment.getAnonymousId().equals(anonymousId)
        );
    }

}
