package com.my.HoopLocater.domain.storageFile;

import com.my.HoopLocater.common.BaseTimeEntity;
import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.hoop.Hoop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is null")
@Entity(name = "storage_image_files")
public class StorageImageFile extends BaseTimeEntity { // 이미지파일

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "anonymous_id")
    private String anonymousId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoop_id")
    private Hoop hoop;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "file_type")
    private String fileType; // 파일 확장자

    @Column(name = "bucket")
    private String bucket;

    @Column(name = "saved_full_path_name")
    private String savedFullPathName;

    @Column(name = "s3_url", columnDefinition = "VARCHAR(500) COMMENT 's3 파일 url'")
    private String s3Url;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public StorageImageFile(Long id, User user, String anonymousId, Hoop hoop, String originalFileName, String fileType, String bucket, String savedFullPathName, String s3Url) {
        this.id = id;
        this.user = user;
        this.anonymousId = anonymousId;
        this.hoop = hoop;
        this.originalFileName = originalFileName;
        this.fileType = fileType;
        this.bucket = bucket;
        this.savedFullPathName = savedFullPathName;
        this.s3Url = s3Url;
    }

    public void changeUser(User user) {
        this.user = user;
    }
    public void delete() {
        this.deletedAt = deletedAt;
    }
}
