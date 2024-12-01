package com.my.HoopLocater.application.s3.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AwsS3ImageUploadCommand {

    private UserDto userDto;
    private String anonymousId;
    private MultipartFile multipartFile;


    public AwsS3ImageUploadCommand(UserDto userDto, String anonymousId, MultipartFile multipartFile) {
        this.userDto = userDto;
        this.anonymousId = anonymousId;
        this.multipartFile = multipartFile;
    }

    public static AwsS3ImageUploadCommand of(UserDto userDto, String anonymousId, MultipartFile multipartFile) {
        return new AwsS3ImageUploadCommand(userDto, anonymousId, multipartFile);
    }

}
