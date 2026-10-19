package com.my.HoopLocater.domain.hoopDetail.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.my.HoopLocater.domain.hoop.LeagueType;
import com.my.HoopLocater.domain.hoopDetail.HoopDetail;

// 프로구단 홈구장 상세정보 DTO
// logoUrl 은 DB 값이 아니라 조회 시점에 OCI PAR URL 을 생성해서 채운다.
public record HoopDetailDto(
        Long hoopId,
        LeagueType leagueType,
        String teamName,
        String gymName,
        String address,
        String homepage,
        String teamCode,
        String logoUrl
) {
    // 조회된 teamCode 기반 DTO 에 파생된 PAR URL 을 붙여 최종 응답 생성
    public HoopDetailDto withLogoUrl(String logoUrl) {
        return new HoopDetailDto(hoopId, leagueType, teamName, gymName, address, homepage, teamCode, logoUrl);
    }

    public static HoopDetailDto from(HoopDetail hoopDetail) {
        return new HoopDetailDto(
                hoopDetail.getHoopId(),
                hoopDetail.getLeagueType(),
                hoopDetail.getTeamName(),
                hoopDetail.getGymName(),
                hoopDetail.getAddress(),
                hoopDetail.getHomepage(),
                hoopDetail.getTeamCode(),
                null // logoUrl 은 서비스 계층에서 PAR URL 생성 후 주입
        );
    }

    @QueryProjection
    public HoopDetailDto(Long hoopId, LeagueType leagueType, String teamName, String gymName, String address, String homepage, String teamCode, String logoUrl) {
        this.hoopId = hoopId;
        this.leagueType = leagueType;
        this.teamName = teamName;
        this.gymName = gymName;
        this.address = address;
        this.homepage = homepage;
        this.teamCode = teamCode;
        this.logoUrl = logoUrl;
    }
}
