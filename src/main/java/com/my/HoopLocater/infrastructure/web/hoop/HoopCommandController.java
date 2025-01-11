package com.my.HoopLocater.infrastructure.web.hoop;

import com.my.HoopLocater.application.hoop.HoopCommandHandler;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopCreateRequest;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class HoopCommandController {

    private final HoopCommandHandler hoopCommandHandler;

    @Operation(
            summary = "농구장 생성 API",
            description = """
                    <p>
                        새로운 농구장 정보를 생성합니다.
                    </p>
                    """
    )
    @PostMapping("/api/v1/hoop/create")
    public HoopDto create(@RequestBody @Valid HoopCreateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return hoopCommandHandler.handler(request.toCommand(anonymousId, userDto));
    }

    @Operation(
            summary = "농구장 정보 업데이트 API",
            description = """
                    <p>
                        농구장 정보를 업데이트합니다.
                    </p>
                    """
    )
    @PutMapping("/api/v1/hoop/update")
    public HoopDto update(@RequestBody @Valid HoopUpdateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        return hoopCommandHandler.handler(request.toCommand(anonymousId, userDto));
    }

}
