package com.my.HoopLocater.infrastructure.web.hoop.dto;

import com.my.HoopLocater.application.hoop.command.HoopCreateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record HoopCreateRequest(
        @Schema(description = "이름", example = "여의도 공원 농구장")
        String name,
        @Schema(description = "위도", example = "11.1111111")
        @NotEmpty
        Double latitude,
        @Schema(description = "경도", example = "11.1111111")
        @NotEmpty
        Double longitude,
        @Schema(description = "골대 갯수", example = "2")
        @NotEmpty
        Integer hoopCount,
        @Schema(description = "바닥 종류", example = "ASPHALT")
        @NotEmpty
        String floorType,
        @Schema(description = "조명 정보", example = "PM9")
        @NotEmpty
        String light,
        @Schema(description = "유/무료", example = "FREE")
        @NotEmpty
        String freeState,
        @Schema(description = "규격(정규코트 여부)", example = "STANDARD")
        @NotEmpty
        String standardState,
        @Schema(description = "로그인 아이디", example = "aaaaaaaa")
        @NotEmpty
        String loginId

) {
    public HoopCreateCommand toCommand() {
        return HoopCreateCommand.of(
                name,
                latitude,
                longitude,
                hoopCount,
                floorType,
                light,
                freeState,
                standardState,
                loginId
        );
    }

}