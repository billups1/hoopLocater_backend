package com.my.HoopLocater.infrastructure.web.report.dto;

import com.my.HoopLocater.application.hoop.command.HoopCreateCommand;
import com.my.HoopLocater.application.report.command.ReportCreateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record ReportCreateRequest(
        @Schema(description = "농구장 id", example = "1")
        @NotEmpty
        Long hoopId,
        @Schema(description = "신고사유", example = "NO_HOOP")
        @NotEmpty
        String reason

) {
    public ReportCreateCommand toCommand() {
        return ReportCreateCommand.of(
                hoopId,
                reason
        );
    }

}