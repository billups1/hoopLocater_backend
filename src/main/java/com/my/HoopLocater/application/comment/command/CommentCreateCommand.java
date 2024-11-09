package com.my.HoopLocater.application.comment.command;

import com.my.HoopLocater.domain.comment.Comment;
import com.my.HoopLocater.domain.hoop.*;
import lombok.Getter;

@Getter
public class CommentCreateCommand {

    private String writer;
    private Long hoopId;
    private String content;

    public CommentCreateCommand(String writer,
                                Long hoopId,
                                String content) {
        this.writer = writer;
        this.hoopId = hoopId;
        this.content = content;
    }

    public static CommentCreateCommand of(String writer,
                                          Long hoopId,
                                          String content) {
        return new CommentCreateCommand(writer, hoopId, content);
    }

    public Comment create() {
        return Comment.builder()
                .writer(writer)
                .hoop(new Hoop(hoopId))
                .content(content)
                .build();
    }
}
