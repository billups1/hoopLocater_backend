package com.my.HoopLocater.infrastructure.web.dto;

public record AppStartCheckResponseDto(
        Boolean updateRequiredCheck,
        Boolean blacklistState
) {
    public static AppStartCheckResponseDto from(Boolean updateRequiredCheck,
                                                Boolean blacklistState) {
        return new AppStartCheckResponseDto(
                updateRequiredCheck,
                blacklistState
        );
    }

}
