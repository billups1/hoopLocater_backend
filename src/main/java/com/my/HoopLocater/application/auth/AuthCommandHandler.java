package com.my.HoopLocater.application.auth;

import com.my.HoopLocater.application.auth.command.AuthGetLoginTokenCommand;
import com.my.HoopLocater.application.auth.command.AuthJoinCommand;
import com.my.HoopLocater.application.auth.command.AuthLoginCommand;
import com.my.HoopLocater.application.auth.command.AuthLogoutCommand;
import com.my.HoopLocater.common.exception.CustomAuthException;
import com.my.HoopLocater.configuration.auth.TokenProvider;
import com.my.HoopLocater.domain.auth.dto.TokenDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class AuthCommandHandler {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    public static String[] LOGIN_ID_BLACKLIST = {"anonymous", "비회원", "admin"};

    @Transactional
    public UserDto handler(AuthJoinCommand command) {
        if (!command.getPassword().equals(command.getPassword_recheck())) {
            throw new CustomAuthException("비밀번호 재입력 정보가 일치하지 않습니다.");
        }
        if (userJpaRepository.existsByLoginId(command.getLoginId())) {
            throw new CustomAuthException("중복 로그인 아이디 입니다.");
        }
        if (userJpaRepository.existsByNickName(command.getNickName())) {
            throw new CustomAuthException("중복 닉네임 입니다.");
        }
        for (String s : LOGIN_ID_BLACKLIST) {
            if (command.getLoginId().contains(s) || command.getNickName().contains(s)) {
                throw new CustomAuthException("아이디 또는 닉네임에 사용할 수 없는 문자가 포함되었습니다.");
            }
        }

        return UserDto.from(userJpaRepository.save(command.create(bCryptPasswordEncoder.encode(command.getPassword()))));
    }

    public UserDto handler(AuthLoginCommand command) {
        var authenticate = authenticationManagerBuilder.getObject().authenticate(command.toAuthenticationToken());

        var user = userJpaRepository.findByLoginId(authenticate.getName()).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });

        return UserDto.from(user);
    }

    public TokenDto handler(AuthGetLoginTokenCommand command) {
        return tokenProvider.generateTokenDtoFromUserDto(command.getUserDto());
    }

}
