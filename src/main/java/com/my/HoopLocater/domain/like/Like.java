package com.my.HoopLocater.domain.like;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "likes")
@Table(
        uniqueConstraints =
        @UniqueConstraint(
                name = "like_uk",
                columnNames = {"writer", "hoop"}
        )
)
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "anonymous_id")
    private String anonymousId;

    @ManyToOne
    @JoinColumn(name = "hoop")
    private Hoop hoop;

    public void changeUser(User user) {
        this.user = user;
    }
}
