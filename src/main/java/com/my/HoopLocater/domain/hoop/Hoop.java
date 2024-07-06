package com.my.HoopLocater.domain.hoop;

import com.my.HoopLocater.application.hoop.HoopEntityListener;
import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@EntityListeners(value = HoopEntityListener.class)
@Entity(name = "hoops")
public class Hoop extends BaseTimeEntity { // 농구장

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

    @Column(name = "comment_count", nullable = false)
    private int commentCount;

    public void updateContent(String name, Integer hoopCount, FloorType floorType, Light light, FreeState freeState, StandardState standardState, String lastChangeUser) {
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.lastChangeUser = lastChangeUser;
    }

    public Hoop(Long id) {
        this.id = id;
    }

    public void addCommentCount() {
        this.commentCount += 1;
    }

    public void minusCommentCount() {
        this.commentCount -= 1;
    }

}
