package com.my.HoopLocater.application.sample.command;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.sample.Subscribe;
import lombok.Getter;

@Getter
public class SubscribeCreateCommand {

    private User fromUser;
    private User toUser;

    public SubscribeCreateCommand(User fromUser,
                                  User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public static SubscribeCreateCommand of(User fromUser,
                                            User toUser) {
        return new SubscribeCreateCommand(fromUser, toUser);
    }

    public Subscribe create() {
        return Subscribe.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
    }
}
