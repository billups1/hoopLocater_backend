package com.my.HoopLocater.application.auth;

import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import com.my.HoopLocater.infrastructure.persistence.comment.CommentJpaQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.my.HoopLocater.application.auth.AuthCommandHandler.LOGIN_ID_BLACKLIST;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthQueryService {
    private final CommentJpaQueryRepository commentJpaQueryRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional(readOnly = true)
    public boolean loginIdDuplicationCheck(String loginId) {
        for (String s : LOGIN_ID_BLACKLIST) {
            if (loginId.contains(s)) {
                return true;
            }
        }
        return userJpaRepository.existsByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean nickNameDuplicationCheck(String nickName) {
        for (String s : LOGIN_ID_BLACKLIST) {
            if (nickName.contains(s)) {
                return true;
            }
        }
        return userJpaRepository.existsByNickName(nickName);
    }
}
