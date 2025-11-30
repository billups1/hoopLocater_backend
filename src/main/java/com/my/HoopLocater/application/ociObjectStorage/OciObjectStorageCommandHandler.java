package com.my.HoopLocater.application.ociObjectStorage;

import com.my.HoopLocater.application.ociObjectStorage.command.OciObjectStorageImageUploadCommand;
import com.my.HoopLocater.common.exception.CustomBusinessException;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.web.storageFile.dto.S3FileDto;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.PutObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OciObjectStorageCommandHandler {

    private final ObjectStorageClient objectStorageClient;

    private final String imageFileRegExp = "^(jpeg|jpg|png|JPEG|JPG|PNG)$";
    private static final String FORMAT_S3_OBJECT_KEY = "%s/%s.%s"; // path(="files/333")/uuid.확장자
    private static final String FORMAT_S3_URL = "%s/%s"; // url/key  ex) https://localhost:5000/files/333/uuid.jpg
    private static final String FORMAT_FOLDER_PATH = "%s/%s"; // 폴더명/userId  ex) files/333

    @Value("${oci.objectstorage.namespace}")
    private String namespaceName;

    @Value("${oci.objectstorage.bucket-name}")
    private String bucketName;

    @Value("${oci.objectstorage.region-id}")
    private String regionId;

    @Transactional
    public S3FileDto handler(OciObjectStorageImageUploadCommand command) {

        try (InputStream inputStream = command.getMultipartFile().getInputStream();) {

            var fileExtension = StringUtils.getFilenameExtension(command.getMultipartFile().getOriginalFilename());
            var path = generateFolder("files", command.getUserDto(), command.getAnonymousId());
            var replacedFileName = UUID.randomUUID().toString();
            var objectName = getS3ObjectKey(path, replacedFileName, fileExtension);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .namespaceName(namespaceName)
                    .bucketName(bucketName)
                    .objectName(objectName)
                    .putObjectBody(inputStream)
                    .contentLength(command.getMultipartFile().getSize())
                    .contentType(command.getMultipartFile().getContentType())
                    .build();

            PutObjectResponse putObjectResponse = objectStorageClient.putObject(putObjectRequest);

            String publicObjectURL = getPublicObjectURL(objectName);

            return new S3FileDto(command.getMultipartFile().getOriginalFilename(), fileExtension, null, objectName, publicObjectURL);

        } catch (IOException e) {
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

    public String getPublicObjectURL(String objectName) {
        String urlFormat = "https://objectstorage.%s.oraclecloud.com/n/%s/b/%s/o/%s";

        return String.format(urlFormat, regionId, namespaceName, bucketName, objectName);
    }

}