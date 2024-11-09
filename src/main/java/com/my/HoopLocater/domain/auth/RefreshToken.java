package com.my.HoopLocater.domain.auth;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"})
@Entity(name = "refreshTokens")
public class RefreshToken extends BaseTimeEntity {

    @Id
    @Column(name = "refresh_token_key")
    private String key; // loginId

    @Column(name = "refresh_token_value")
    private String value; // refreshToken 값

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}