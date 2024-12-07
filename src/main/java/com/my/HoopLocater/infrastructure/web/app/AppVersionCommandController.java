package com.my.HoopLocater.infrastructure.web.app;

import com.my.HoopLocater.application.app.AppVersionCommandHandler;
import com.my.HoopLocater.infrastructure.web.app.dto.AppUpdateRequiredCheckRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
