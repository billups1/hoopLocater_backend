package com.my.HoopLocater.application.storageFile.command;

import com.my.HoopLocater.domain.auth.User;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.storageFile.StorageImageFile;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.S3FileDto;
import lombok.Getter;

@Getter
public class StorageFileCreateCommand {

    private UserDto userDto;
    private String anonymousId;
    private S3FileDto s3FileDto;
    private Hoop hoop;

    public StorageFileCreateCommand(UserDto userDto, String anonymousId, S3FileDto s3FileDto, Hoop hoop) {
        this.userDto = userDto;
        this.anonymousId = anonymousId;
        this.s3FileDto = s3FileDto;
        this.hoop = hoop;
    }

    public static StorageFileCreateCommand of(UserDto userDto, String anonymousId, S3FileDto s3FileDto, Hoop hoop) {
        return new StorageFileCreateCommand(userDto, anonymousId, s3FileDto, hoop);
    }

    public StorageImageFile create() {
        return StorageImageFile.builder()
                .user(userDto == null? null : new User(userDto.id()))
                .anonymousId(anonymousId)
                .hoop(hoop)
                .originalFileName(s3FileDto.originalFileName())
                .fileType(s3FileDto.fileType())
                .bucket(s3FileDto.bucket())
                .savedFullPathName(s3FileDto.savedFullPathName())
                .s3Url(s3FileDto.fileS3Url())
                .build();
    }
}
