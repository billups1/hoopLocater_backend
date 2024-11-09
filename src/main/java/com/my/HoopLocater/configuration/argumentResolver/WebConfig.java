package com.my.HoopLocater.configuration.argumentResolver;

import com.my.HoopLocater.configuration.auth.TokenProvider;
import com.my.HoopLocater.infrastructure.persistence.auth.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserDtoArgumentResolver(tokenProvider, userJpaRepository));
    }

}
