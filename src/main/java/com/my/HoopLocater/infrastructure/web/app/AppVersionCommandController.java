package com.my.HoopLocater.infrastructure.web.app;

import com.my.HoopLocater.application.app.AppVersionCommandHandler;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.app.dto.AppStartCheckRequest;
import com.my.HoopLocater.infrastructure.web.app.dto.AppUpdateRequiredCheckRequest;
import com.my.HoopLocater.infrastructure.web.dto.AppStartCheckResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class AppVersionCommandController {
    private final AppVersionCommandHandler commandHandler;

    @Operation(
            summary = "앱 업데이트 필요여부 조회 API",
            description = """
                    <p>
                        앱 업데이트를 해야하는 상태인지 확인합니다.<br>
                        true = 업데이트 해야 함<br>
                        false = 업데이트 필요 없음
                    </p>
                    """
    )
    @PostMapping("/api/v1/appVersion/updateRequiredCheck")
    public Boolean updateRequiredCheck(@RequestBody @Valid AppUpdateRequiredCheckRequest request) {
        return commandHandler.handler(request.toCommand());
    }

    @Operation(
            summary = "앱 시작 체크 API",
            description = """
                    <p>
                        앱 업데이트 상태, 차단 아이피, 차단 user를 체크합니다.<br>
                    </p>
                    """
    )
    @PostMapping("/api/v1/appStartCheck")
    public AppStartCheckResponseDto appStartCheck(@RequestBody @Valid AppStartCheckRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return commandHandler.handler(request.toCommand(anonymousId, userDto));
    }

}
