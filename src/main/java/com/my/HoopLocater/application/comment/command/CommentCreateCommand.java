package com.my.HoopLocater.application.comment.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.Comment;
import com.my.HoopLocater.domain.hoop.*;
import lombok.Getter;

@Getter
public class CommentCreateCommand {

    private Long hoopId;
    private String content;
    private String anonymousId;
    private UserDto userDto;

    public CommentCreateCommand(Long hoopId,
                                String content,
                                String anonymousId,
                                UserDto userDto) {
        this.hoopId = hoopId;
        this.content = content;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static CommentCreateCommand of(Long hoopId,
                                          String content,
                                          String anonymousId,
                                          UserDto userDto) {
        return new CommentCreateCommand(hoopId, content, anonymousId, userDto);
    }

    public Comment create() {
        return Comment.builder()
                .writer(userDto == null ? anonymousId : userDto.nickName())
                .hoop(new Hoop(hoopId))
                .content(content)
                .build();
    }
}
