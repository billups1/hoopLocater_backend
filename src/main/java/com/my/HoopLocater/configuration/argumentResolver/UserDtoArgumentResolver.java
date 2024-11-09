package com.my.HoopLocater.configuration.argumentResolver;

import com.my.HoopLocater.configuration.auth.TokenProvider;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class UserDtoArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;
    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserDto.class) && parameter.hasParameterAnnotation(AuthUserDto.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = tokenProvider.resolveAccessToken((HttpServletRequest) webRequest.getNativeRequest());
        if (accessToken == "" || accessToken == null || !tokenProvider.validateToken(accessToken)) {
            return null;
        }
        var user = userJpaRepository.findByLoginId(tokenProvider.parseClaims(accessToken).getSubject());
        if (user.isEmpty()) {
            return null;
        }
        return UserDto.from(user.get());
    }
}
