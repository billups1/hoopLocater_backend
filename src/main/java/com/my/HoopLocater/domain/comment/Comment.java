package com.my.HoopLocater.domain.comment;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "writer")
    private String writer;

    @ManyToOne
    @JoinColumn(name = "hoop_id")
    private Hoop hoop;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Builder
    public Comment(String writer, Hoop hoop, String content) {
        this.writer = writer;
        this.hoop = hoop;
        this.content = content;
    }

    public Comment(Long id) {
        this.id = id;
    }

}
