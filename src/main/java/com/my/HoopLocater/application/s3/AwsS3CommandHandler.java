package com.my.HoopLocater.application.s3;

import com.my.HoopLocater.application.s3.command.AwsS3ImageUploadCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.S3FileDto;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AwsS3CommandHandler {

    private final S3Operations s3Operations;

    private final String imageFileRegExp = "^(jpeg|jpg|png|JPEG|JPG|PNG)$";
    private static final String FORMAT_S3_OBJECT_KEY = "%s/%s.%s"; // path(="files/333")/uuid.확장자
    private static final String FORMAT_S3_URL = "%s/%s"; // url/key  ex) https://localhost:5000/files/333/uuid.jpg
    private static final String FORMAT_FOLDER_PATH = "%s/%s"; // 폴더명/userId  ex) files/333

    @Value("${spring.cloud.aws.s3.url}")
    private String s3Url;
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public S3FileDto handler(AwsS3ImageUploadCommand command) {
        var fileExtension = StringUtils.getFilenameExtension(command.getMultipartFile().getOriginalFilename());
        if (!fileExtension.matches(imageFileRegExp)) { // 파일 확장자 체크
            throw new CustomBusinessException("이미지 파일만 업로드 가능합니다.");
        }

        var path = generateFolder("files", command.getUserDto(), command.getAnonymousId());
        var replacedFileName = UUID.randomUUID().toString();
        var key = getS3ObjectKey(path, replacedFileName, fileExtension);

        try (var fileIo = command.getMultipartFile().getInputStream()) {
            s3Operations.upload(bucket, key, fileIo, ObjectMetadata.builder().contentType(command.getMultipartFile().getContentType()).build());
            var fileS3Url = getS3Url(s3Url, key);
            return new S3FileDto(command.getMultipartFile().getOriginalFilename(), fileExtension, bucket, key, fileS3Url);
        } catch (Exception e) {
            throw new CustomBusinessException("이미지 파일 업로드 오류입니다.");
        }
    }

    private static String generateFolder(String prefix, UserDto userDto, String anonymousId) {
        if (userDto == null) {
            return String.format(FORMAT_FOLDER_PATH, prefix, anonymousId);
        } else {
            return String.format(FORMAT_FOLDER_PATH, prefix, userDto.id());
        }
    }
    private static String getS3ObjectKey(String path, String fileName, String fileExtension) {
        return String.format(FORMAT_S3_OBJECT_KEY, path, fileName, fileExtension);
    }
    private static String getS3Url(String s3Url, String objectKey) {
        return String.format(FORMAT_S3_URL, s3Url, objectKey);
    }

}
