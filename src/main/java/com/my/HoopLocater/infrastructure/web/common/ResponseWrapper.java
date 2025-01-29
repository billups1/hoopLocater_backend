package com.my.HoopLocater.infrastructure.web.common;

import com.my.HoopLocater.common.log.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.my.HoopLocater.infrastructure.web")
@RequiredArgsConstructor
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    private final LogUtil logUtil;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        logUtil.insertLog();
        return ApiResponseDto.builder()
                .code(ApiResponseCode.SUCCESS.getCode())
                .data(body)
                .message(ApiResponseCode.SUCCESS.getMessage())
                .build();
    }

}
