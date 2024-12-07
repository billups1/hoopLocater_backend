package com.my.HoopLocater.application.sample.command;

import com.my.HoopLocater.domain.auth.User;
import lombok.Getter;

@Getter
public class SubscribeDeleteCommand {

    private User fromUser;
    private User toUser;

    public SubscribeDeleteCommand(User fromUser,
                                  User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public static SubscribeDeleteCommand of(User fromUser,
                                            User toUser) {
        return new SubscribeDeleteCommand(fromUser, toUser);
    }

}
