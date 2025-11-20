package com.my.HoopLocater.application.app.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AppStartCheckCommand {

    private Integer versionCode;
    private String versionName;
    private String anonymousId;
    private UserDto userDto;

    public AppStartCheckCommand(Integer versionCode,
                                String versionName,
                                String anonymousId,
                                UserDto userDto) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.anonymousId = anonymousId;
        this.userDto = userDto;
    }

    public static AppStartCheckCommand of(Integer versionCode,
                                          String versionName,
                                          String anonymousId,
                                          UserDto userDto) {
        return new AppStartCheckCommand(versionCode, versionName, anonymousId, userDto);
    }

}
