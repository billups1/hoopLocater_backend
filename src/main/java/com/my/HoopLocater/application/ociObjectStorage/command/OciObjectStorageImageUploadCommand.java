package com.my.HoopLocater.application.ociObjectStorage.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class OciObjectStorageImageUploadCommand {

    private UserDto userDto;
    private String anonymousId;
    private MultipartFile multipartFile;


    public OciObjectStorageImageUploadCommand(UserDto userDto, String anonymousId, MultipartFile multipartFile) {
        this.userDto = userDto;
        this.anonymousId = anonymousId;
        this.multipartFile = multipartFile;
    }

    public static OciObjectStorageImageUploadCommand of(UserDto userDto, String anonymousId, MultipartFile multipartFile) {
        return new OciObjectStorageImageUploadCommand(userDto, anonymousId, multipartFile);
    }

}
