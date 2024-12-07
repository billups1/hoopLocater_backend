package com.my.HoopLocater.domain.app;

import com.my.HoopLocater.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "app_versions")
public class AppVersion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version_code")
    private Integer versionCode; // ex) 13

    @Column(name = "version_name")
    private String versionName; // ex) 1.1

    @Column(name = "app_update_required_state")
    @Enumerated(EnumType.STRING)
    private AppUpdateRequiredState appUpdateRequiredState;

}
