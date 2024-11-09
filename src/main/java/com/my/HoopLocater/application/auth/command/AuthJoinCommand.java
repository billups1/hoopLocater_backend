package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.Role;
import com.my.HoopLocater.domain.auth.User;
import lombok.Getter;

@Getter
public class AuthJoinCommand {

    private String loginId;
    private String password;
    private String password_recheck;
    private String nickName;

    public AuthJoinCommand(String loginId,
                           String password,
                           String password_recheck,
                           String nickName) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
        this.password_recheck = password_recheck;
    }

    public static AuthJoinCommand of(String loginId,
                                       String password,
                                       String password_recheck,
                                       String nickName) {
        return new AuthJoinCommand(loginId, password, password_recheck, nickName);
    }

    public User create(String encodedPassword) {
        return User.builder()
                .loginId(loginId)
                .nickName(nickName)
                .password(encodedPassword)
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
