package com.my.HoopLocater.infrastructure.web.report;

import com.my.HoopLocater.application.report.ReportCommandHandler;
import com.my.HoopLocater.configuration.argumentResolver.AuthUserDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.report.dto.ReportCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
@RestController
public class ReportCommandController {
    private final ReportCommandHandler commandHandler;

    @Operation(
            summary = "농구장 신고 API",
            description = """
                    <p>
                        농구장 신고 API
                    </p>
                    """
    )
    @PostMapping("/create")
    public void create(@RequestBody @Valid ReportCreateRequest request, @RequestHeader("anonymousId") String anonymousId, @AuthUserDto UserDto userDto) {
        commandHandler.handler(request.toCommand(anonymousId, userDto));
    }

}
