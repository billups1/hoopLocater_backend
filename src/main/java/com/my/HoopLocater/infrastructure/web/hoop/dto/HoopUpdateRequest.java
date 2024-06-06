package com.my.HoopLocater.infrastructure.web.hoop.dto;

import com.my.HoopLocater.application.hoop.command.HoopUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record HoopUpdateRequest(
        @Schema(description = "id", example = "1")
        Long id,
        @Schema(description = "농구장 이름", example = "여의도 공원 농구장")
        String name,
        @Schema(description = "골대 갯수", example = "2")
        @NotEmpty
        Integer hoopCount,
        @Schema(description = "바닥 종류", example = "ASPHALT")
        @NotEmpty
        String floorType,
        @Schema(description = "조명 정보", example = "PM9")
        @NotEmpty
        String light

) {
    public HoopUpdateCommand toCommand() {
        return HoopUpdateCommand.of(
                id,
                name,
                hoopCount,
                floorType,
                light
        );
    }

}