package com.my.HoopLocater.infrastructure.web.auth;

import com.my.HoopLocater.application.auth.AuthCommandHandler;
import com.my.HoopLocater.application.auth.command.AuthGetLoginTokenCommand;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.TokenDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.auth.dto.AuthJoinRequest;
import com.my.HoopLocater.infrastructure.web.auth.dto.AuthLoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.my.HoopLocater.configuration.auth.TokenProvider.ACCESS_TOKEN_HEADER;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class AuthCommandController {
    private final AuthCommandHandler commandHandler;

    @Operation(
            summary = "회원가입 API",
            description = """
                    <p>
                        유저 회원가입 요청
                    </p>
                    """
    )
    @PostMapping("/api/v1/auth/join")
    public UserDto join(@RequestBody @Valid AuthJoinRequest request, HttpServletResponse response) {
        var userDto = commandHandler.handler(request.toCommand());
        var tokenDto = commandHandler.handler(AuthGetLoginTokenCommand.of(userDto));

        setAccessTokenInHeader(response, tokenDto);

        return userDto;
    }

    @Operation(
            summary = "이메일 로그인 API",
            description = """
                    <p>
                        유저 이메일 로그인 요청
                    </p>
                    """
    )
    @PostMapping("/api/v1/auth/login")
    public UserDto login(@RequestBody @Valid AuthLoginRequest request, HttpServletResponse response) {
        var userDto = commandHandler.handler(request.toCommand());
        var tokenDto = commandHandler.handler(AuthGetLoginTokenCommand.of(userDto));

        setAccessTokenInHeader(response, tokenDto);

        return userDto;
    }

    @Operation(
            summary = "내 정보 조회 API",
            description = """
                    <p>
                        로그인한 유저의 정보 반환
                        토큰이 없거나 만료되었거나 잘못되었다면 null 반환
                    </p>
                    """
    )
    @GetMapping("/api/v1/auth/myInfo")
    public UserDto myInfo(@AuthUserDto UserDto userDto, HttpServletRequest request) {
        return userDto;
    }

    private static void setAccessTokenInHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(ACCESS_TOKEN_HEADER, tokenDto.accessToken());
    }

}
