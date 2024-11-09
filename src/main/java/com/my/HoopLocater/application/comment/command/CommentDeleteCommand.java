package com.my.HoopLocater.application.comment.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class CommentDeleteCommand {

    private Long id;
    private String writer;
    private UserDto userDto;

    public CommentDeleteCommand(Long id,
                                String writer,
                                UserDto userDto) {
        this.id = id;
        this.writer = writer;
        this.userDto = userDto;
    }

    public static CommentDeleteCommand of(Long id,
                                          String anonymousId,
                                          UserDto userDto) {
        return new CommentDeleteCommand(id, anonymousId, userDto);
    }

}
