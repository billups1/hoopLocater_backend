package com.my.HoopLocater.domain.auth.dto;

public record TokenDto(
        String grantType,
        String accessToken,
        Long accessTokenExpiresIn
) {
    public static TokenDto from(String grantType, String accessToken, Long accessTokenExpiresIn) {
        return new TokenDto(grantType, accessToken, accessTokenExpiresIn);
    }
}