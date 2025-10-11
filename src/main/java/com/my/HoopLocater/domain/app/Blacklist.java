package com.my.HoopLocater.domain.app;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "blacklist")
public class Blacklist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "anonymous_id")
    private String anonymousId;

    @Column(name = "blacklist_type")
    @Enumerated(EnumType.STRING)
    private BlacklistType blacklistType;

}
