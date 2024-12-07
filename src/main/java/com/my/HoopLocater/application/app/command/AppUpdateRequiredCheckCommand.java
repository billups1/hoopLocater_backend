package com.my.HoopLocater.application.app.command;

import lombok.Getter;

@Getter
public class AppUpdateRequiredCheckCommand {

    private Integer versionCode;
    private String versionName;

    public AppUpdateRequiredCheckCommand(Integer versionCode,
                                         String versionName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public static AppUpdateRequiredCheckCommand of(Integer versionCode,
                                                   String versionName) {
        return new AppUpdateRequiredCheckCommand(versionCode, versionName);
    }

}
