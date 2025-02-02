package com.my.HoopLocater.domain.auth;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("withdrawal_at is null")
@Entity(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "provider")
    private String provider; // 소셜로그인 업체

    @Column(name = "withdrawal_at")
    private LocalDateTime withdrawalAt; // 탈퇴시점

    public User(Long id) {
        this.id = id;
    }

    @Builder
    public User(Long id, String loginId, String password, String nickName, Role role, String provider, LocalDateTime withdrawalAt) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
        this.provider = provider;
        this.withdrawalAt = withdrawalAt;
    }

    public void softDelete() { // 탈퇴
        this.withdrawalAt = LocalDateTime.now();
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserInfo(String nickName) {
        this.nickName = nickName;
    }

    public void defaultLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
