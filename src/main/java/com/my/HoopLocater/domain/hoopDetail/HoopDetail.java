package com.my.HoopLocater.domain.hoopDetail;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.hoop.LeagueType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "hoop_details")
public class HoopDetail extends BaseTimeEntity { // 프로구단 홈구장 상세정보

    @Id
    @Column(name = "hoop_id", nullable = false)
    private Long hoopId; // hoops 테이블의 id를 PK로 공유

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoop_id")
    private Hoop hoop;

    @Column(name = "league_type")
    @Enumerated(EnumType.STRING)
    private LeagueType leagueType; // 리그 구분(KBL / WKBL)

    @Column(name = "team_name")
    private String teamName; // 구단명

    @Column(name = "gym_name")
    private String gymName; // 체육관 이름

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "homepage")
    private String homepage; // 구단 홈페이지

    @Column(name = "team_code")
    private String teamCode; // 구단 코드 (OCI 저장 경로 team/{teamCode}.png 로고 파일명)

    @Builder
    public HoopDetail(Hoop hoop, LeagueType leagueType, String teamName, String gymName, String address, String homepage, String teamCode) {
        this.hoop = hoop;
        this.leagueType = leagueType;
        this.teamName = teamName;
        this.gymName = gymName;
        this.address = address;
        this.homepage = homepage;
        this.teamCode = teamCode;
    }

    public void updateContent(LeagueType leagueType, String teamName, String gymName, String address, String homepage, String teamCode) {
        this.leagueType = leagueType;
        this.teamName = teamName;
        this.gymName = gymName;
        this.address = address;
        this.homepage = homepage;
        this.teamCode = teamCode;
    }

}
