package com.my.HoopLocater.domain.comment;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "comments")
public class Comment extends BaseTimeEntity {

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
    @JoinColumn(name = "hoop_id")
    private Hoop hoop;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    public Comment(Long id) {
        this.id = id;
    }

    public void changeUser(User user) {
        this.user = user;
    }
}
