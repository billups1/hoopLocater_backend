package com.my.HoopLocater.infrastructure.web.storageFile.dto;

import com.my.HoopLocater.domain.storageFile.StorageImageFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ImageFileResponseDto(
        Long id,
        Long userId,
        String nickName,
        Long hoopId,
        String fileS3Url,
        String createdDate,
        Boolean myImageFileState
) {
    public static ImageFileResponseDto from(Long id,
                                            Long userId,
                                            String nickName,
                                            Long hoopId,
                                            String fileS3Url,
                                            LocalDateTime createdAt,
                                            Boolean myImageFileState) {
        return new ImageFileResponseDto(
                id,
                userId,
                nickName,
                hoopId,
                fileS3Url,
                createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                myImageFileState
        );
    }

    public static ImageFileResponseDto from(StorageImageFile storageImageFile, Boolean myImageFileState) {
        return new ImageFileResponseDto(
                storageImageFile.getId(),
                storageImageFile.getUser() == null ? null : storageImageFile.getUser().getId(),
                storageImageFile.getUser() == null ? storageImageFile.getAnonymousId() : storageImageFile.getUser().getNickName(),
                storageImageFile.getHoop().getId(),
                storageImageFile.getS3Url(),
                storageImageFile.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                myImageFileState
        );
    }

}
