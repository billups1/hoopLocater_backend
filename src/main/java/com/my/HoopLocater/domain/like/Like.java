package com.my.HoopLocater.domain.like;

import com.my.HoopLocater.common.BaseTimeEntity;
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

    @Column(name = "writer")
    private String writer;

    @ManyToOne
    @JoinColumn(name = "hoop")
    private Hoop hoop;

}
