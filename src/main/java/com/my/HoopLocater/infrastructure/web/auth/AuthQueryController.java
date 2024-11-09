package com.my.HoopLocater.infrastructure.web.auth;

import com.my.HoopLocater.application.auth.AuthQueryService;
import com.my.HoopLocater.common.exception.CustomAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.my.HoopLocater.application.auth.AuthCommandHandler.LOGIN_ID_BLACKLIST;

@Tag(name = "Auth(유저계정) 관련 조회 API")
@RequiredArgsConstructor
@RestController
public class AuthQueryController {

    private final AuthQueryService service;

    @Operation(summary = "로그인아이디 중복 체크")
    @GetMapping("/api/v1/auth/loginId-duplication-check/{loginId}")
    public boolean loginIdDuplicationCheck(@PathVariable(name = "loginId") String loginId) {
        return service.loginIdDuplicationCheck(loginId);
    }

    @Operation(summary = "닉네임 중복 체크")
    @GetMapping("/api/v1/auth/nickName-duplication-check/{nickName}")
    public boolean nickNameDuplicationCheck(@PathVariable(name = "nickName") String nickName) {
        return service.nickNameDuplicationCheck(nickName);
    }

}
