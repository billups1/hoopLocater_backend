package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.User;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class AuthLoginCommand {

    private String loginId;
    private String password;

    public AuthLoginCommand(String loginId,
                            String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public static AuthLoginCommand of(String loginId,
                                      String password) {
        return new AuthLoginCommand(loginId, password);
    }

    public User create() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(loginId, password);
    }
}
