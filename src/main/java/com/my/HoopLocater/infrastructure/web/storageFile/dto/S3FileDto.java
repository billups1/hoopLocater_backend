package com.my.HoopLocater.infrastructure.web.storageFile.dto;

public record S3FileDto(
        String originalFileName,
        String fileType,
        String bucket,
        String savedFullPathName,
        String fileS3Url
) {
}
