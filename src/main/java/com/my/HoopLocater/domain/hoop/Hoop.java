package com.my.HoopLocater.domain.hoop;

import com.my.HoopLocater.application.hoop.HoopEntityListener;
import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "hoop", fetch = FetchType.LAZY)
    private List<StorageImageFile> storageImageFiles = new ArrayList<>(); // 농구장의 사진

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "anonymous_id")
    private String anonymousId;

    @Column(name = "comment_count", nullable = false)
    private Integer commentCount;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Builder
    public Hoop(Long id, String name, Double latitude, Double longitude, Integer hoopCount, FloorType floorType, Light light, FreeState freeState, StandardState standardState, List<StorageImageFile> storageImageFiles, User user, String anonymousId, int commentCount, int likeCount) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.storageImageFiles = storageImageFiles;
        this.user = user;
        this.anonymousId = anonymousId;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }

    public void updateContent(String name, Integer hoopCount, FloorType floorType, Light light, FreeState freeState, StandardState standardState, User user, String anonymousId) {
        this.name = name;
        this.hoopCount = hoopCount;
        this.floorType = floorType;
        this.light = light;
        this.freeState = freeState;
        this.standardState = standardState;
        this.user = user;
        this.anonymousId = anonymousId;
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

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void minusLikeCount() {
        this.likeCount -= 1;
    }

    public void changeUser(User user) {
        this.user = user;
    }
}
