package com.my.HoopLocater.configuration.argumentResolver;

import io.swagger.v3.oas.annotations.Hidden;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Hidden
public @interface AuthUserDto {
    // 프론트단에서 넘어오는 JWT를 해석하여 UserDto를 반환합니다.
    // 이때 JWT가 잘못되었거나 JWT가 없다면, null을 반환합니다.
}
