package com.my.HoopLocater.domain.hoopDetail.dto;

import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.LeagueType;
import com.my.HoopLocater.domain.hoopDetail.HoopDetail;
import com.querydsl.core.annotations.QueryProjection;

// 프로구단 홈구장(KBL/WKBL) 조회용 DTO
// logoUrl 은 DB 값이 아니라 조회 시점에 OCI PAR URL 을 생성해서 채운다.
public record ProHoopDto(
        Long id,
        String name,       // 체육관 이름
        LeagueType type,   // KBL / WKBL
        Double latitude,
        Double longitude,
        String teamName,   // 구단명
        String address,    // 주소
        String teamCode,   // 구단 코드 (OCI 저장 경로 team/{teamCode}.png)
        String logoUrl     // 구단 로고 PAR url (마커 아이콘용, 서비스에서 생성)
) {
    // 조회된 teamCode 기반 DTO 에 파생된 PAR URL 을 붙여 최종 응답 생성
    public ProHoopDto withLogoUrl(String logoUrl) {
        return new ProHoopDto(id, name, type, latitude, longitude, teamName, address, teamCode, logoUrl);
    }

    @QueryProjection
    public ProHoopDto(Hoop hoop, HoopDetail hoopDetail) {
        this(
                hoop.getId(),
                hoop.getName(),
                hoop.getType(),
                hoop.getLatitude(),
                hoop.getLongitude(),
                hoopDetail.getTeamName(),
                hoopDetail.getAddress(),
                hoopDetail.getTeamCode(),
                null // logoUrl 은 서비스 계층에서 PAR URL 생성 후 주입
        );
    }
}
