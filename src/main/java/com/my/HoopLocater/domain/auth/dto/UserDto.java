package com.my.HoopLocater.domain.auth.dto;

import com.my.HoopLocater.domain.auth.Role;
import com.my.HoopLocater.domain.auth.User;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String loginId,
        String nickName,
        Role role,
        LocalDateTime withdrawalAt

) {
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getLoginId(),
                user.getNickName(),
                user.getRole(),
                user.getWithdrawalAt()
        );
    }
}
