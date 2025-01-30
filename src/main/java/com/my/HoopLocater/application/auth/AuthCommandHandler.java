package com.my.HoopLocater.application.auth;

import com.my.HoopLocater.application.auth.command.*;
import com.my.HoopLocater.common.exception.CustomAuthException;
import com.my.HoopLocater.configuration.auth.TokenProvider;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.auth.dto.TokenDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.comment.Comment;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.like.Like;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.comment.CommentJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.hoop.HoopJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.like.LikeJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.storageFile.StorageFileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthCommandHandler {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final StorageFileJpaRepository storageFileJpaRepository;
    private final HoopJpaRepository hoopJpaRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final LikeJpaRepository likeJpaRepository;
    private final TokenProvider tokenProvider;
    private final AuthQueryService authQueryService;
    public static String[] LOGIN_ID_BLACKLIST = {"anonymous", "비회원", "admin"};
    public static Long WITHDRAWAL_USER_ID = 10L;

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

    @Transactional
    public UserDto handler(AuthLoginCommand command) {
        var authenticate = authenticationManagerBuilder.getObject().authenticate(command.toAuthenticationToken());

        var user = userJpaRepository.findByLoginId(authenticate.getName()).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });

        return UserDto.from(user);
    }

    @Transactional
    public TokenDto handler(AuthGetLoginTokenCommand command) {
        return tokenProvider.generateTokenDtoFromUserDto(command.getUserDto());
    }

    @Transactional
    public void handler(AuthWithdrawalCommand command) {
        User user = userJpaRepository.findById(command.getUserDto().id()).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });
        if (!bCryptPasswordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new CustomAuthException("비밀번호가 일치하지 않습니다.");
        }

        // 탈퇴 유저가 만든 데이터의 user를 탈퇴 회원으로 바꾸기
        User withrawalUser = User.builder().id(WITHDRAWAL_USER_ID).build();
        for (StorageImageFile storageImageFile : storageFileJpaRepository.findAllByUser(user)) {
            storageImageFile.changeUser(withrawalUser); // 탈퇴 회원으로 변경
        }
        for (Hoop hoop : hoopJpaRepository.findAllByUser(user)) {
            hoop.changeUser(withrawalUser);
        }
        for (Comment comment : commentJpaRepository.findAllByUser(user)) {
            comment.changeUser(withrawalUser);
        }
        for (Like like : likeJpaRepository.findAllByUser(user)) {
            like.changeUser(withrawalUser);
        }

        user.softDelete();
    }

    @Transactional
    public UserDto handler(AuthPasswordResetCommand command) {
        User user = userJpaRepository.findById(command.getUserDto().id()).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });
        if (!bCryptPasswordEncoder.matches(command.getCurrentPassword(), user.getPassword())) {
            throw new CustomAuthException("현재 비밀번호가 일치하지 않습니다.");
        }
        if (!command.getNewPassword().equals(command.getNewPasswordRecheck())) {
            throw new CustomAuthException("비밀번호 재입력 정보가 일치하지 않습니다.");
        }

        user.updatePassword(bCryptPasswordEncoder.encode(command.getNewPassword()));

        return UserDto.from(user);
    }

    @Transactional
    public UserDto handler(AuthProfileSettingCommand command) {
        if (authQueryService.nickNameDuplicationCheck(command.getNickName())) {
            throw new CustomAuthException("중복 닉네임 입니다.");
        }
        User user = userJpaRepository.findById(command.getUserDto().id()).orElseThrow(() -> {
            throw new CustomAuthException("입력된 로그인 id로 회원을 찾을 수 없습니다.");
        });
        user.updateUserInfo(command.getNickName());
        return UserDto.from(user);
    }
}
