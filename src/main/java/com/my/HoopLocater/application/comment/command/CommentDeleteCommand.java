package com.my.HoopLocater.application.comment.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class CommentDeleteCommand {

    private Long id;
    private UserDto userDto;

    public CommentDeleteCommand(Long id,
                                UserDto userDto) {
        this.id = id;
        this.userDto = userDto;
    }

    public static CommentDeleteCommand of(Long id,
                                          UserDto userDto) {
        return new CommentDeleteCommand(id, userDto);
    }

}
