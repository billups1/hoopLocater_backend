package com.my.HoopLocater.domain.report;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "reports")
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoop_id", nullable = false)
    private Hoop hoop;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "anonymous_id")
    private String anonymousId;

    public Report(Long id) {
        this.id = id;
    }
}
