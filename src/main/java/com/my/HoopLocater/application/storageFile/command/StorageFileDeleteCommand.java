package com.my.HoopLocater.application.storageFile.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class StorageFileDeleteCommand {

    private UserDto userDto;
    private Long imageId;

    public StorageFileDeleteCommand(UserDto userDto, Long imageId) {
        this.userDto = userDto;
        this.imageId = imageId;
    }

    public static StorageFileDeleteCommand of(UserDto userDto, Long imageId) {
        return new StorageFileDeleteCommand(userDto, imageId);
    }

}
