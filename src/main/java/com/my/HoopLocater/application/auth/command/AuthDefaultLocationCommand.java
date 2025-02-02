package com.my.HoopLocater.application.auth.command;

import com.my.HoopLocater.domain.auth.dto.UserDto;
import lombok.Getter;

@Getter
public class AuthDefaultLocationCommand {

    private Double latitude;
    private Double longitude;
    private UserDto userDto;

    public AuthDefaultLocationCommand(Double latitude, Double longitude, UserDto userDto) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.userDto = userDto;
    }

    public static AuthDefaultLocationCommand of(Double latitude, Double longitude, UserDto userDto) {
        return new AuthDefaultLocationCommand(latitude, longitude, userDto);
    }

}
