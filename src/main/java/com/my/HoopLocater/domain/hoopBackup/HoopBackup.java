package com.my.HoopLocater.domain.hoopBackup;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.hoop.FloorType;
import com.my.HoopLocater.domain.hoop.FreeState;
import com.my.HoopLocater.domain.hoop.Light;
import com.my.HoopLocater.domain.hoop.StandardState;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "hoop_backups")
public class HoopBackup extends BaseTimeEntity { // 농구장 백업

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude", nullable = false)
    private Double latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; // 경도

    @Column(name = "hoopCount")
    private Integer hoopCount; // 골대 갯수

    @Column(name = "floorType")
    @Enumerated(EnumType.STRING)
    private FloorType floorType; // 바닥 종류

    @Column(name = "light")
    @Enumerated(EnumType.STRING)
    private Light light; // 조명

    @Column(name = "freeState")
    @Enumerated(EnumType.STRING)
    private FreeState freeState; // 유/무료

    @Column(name = "standardState")
    @Enumerated(EnumType.STRING)
    private StandardState standardState; // 규격(정규코트 여부)

    @Column(name = "lastChangeUser")
    private String lastChangeUser;

    @Column(name = "updateMethod")
    @Enumerated(EnumType.STRING)
    private UpdateMethod updateMethod; // 업데이트 방법

    public void updateContent(String name, Integer hoopCount, FloorType floorType, Light light, FreeState freeState, StandardState standardState, String lastChangeUser) {
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.lastChangeUser = lastChangeUser;
    }

    public HoopBackup(Long id) {
        this.id = id;
    }

}
