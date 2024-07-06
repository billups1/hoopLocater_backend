package com.my.HoopLocater.application.comment.command;

import lombok.Getter;

@Getter
public class CommentDeleteCommand {

    private Long id;
    private String writer;

    public CommentDeleteCommand(Long id,
                                String writer) {
        this.id = id;
        this.writer = writer;
    }

    public static CommentDeleteCommand of(Long id,
                                          String writer) {
        return new CommentDeleteCommand(id, writer);
    }

}
