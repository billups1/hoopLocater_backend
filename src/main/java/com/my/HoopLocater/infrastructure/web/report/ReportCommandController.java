package com.my.HoopLocater.infrastructure.web.report;

import com.my.HoopLocater.application.hoop.HoopCommandHandler;
import com.my.HoopLocater.application.report.ReportCommandHandler;
import com.my.HoopLocater.domain.hoop.dto.HoopDto;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopCreateRequest;
import com.my.HoopLocater.infrastructure.web.hoop.dto.HoopUpdateRequest;
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
    public void create(@RequestBody @Valid ReportCreateRequest request) {
        commandHandler.handler(request.toCommand());
    }

}
