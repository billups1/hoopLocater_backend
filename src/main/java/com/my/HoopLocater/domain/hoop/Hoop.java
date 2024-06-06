package com.my.HoopLocater.domain.hoop;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "hoops")
public class Hoop extends BaseTimeEntity { // 농구장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude; // 위도

    @Column(name = "longitude")
    private Double longitude; // 경도

    @Column(name = "hoopCount")
    private Integer hoopCount; // 골대 갯수

    @Column(name = "floorType")
    @Enumerated(EnumType.STRING)
    private FloorType floorType; // 바닥 종류

    @Column(name = "light")
    @Enumerated(EnumType.STRING)
    private Light light; // 조명

    public void updateContent(String name, Integer hoopCount, FloorType floorType, Light light) {
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
    }

    public Hoop(Long id) {
        this.id = id;
    }

}
