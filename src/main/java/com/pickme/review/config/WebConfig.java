package com.pickme.review.config;

import com.pickme.review.config.security.JWTInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor).addPathPatterns("/review/**") // /calendar/** 경로에 인터셉터 적용
                .excludePathPatterns("/swagger-ui/**"); // /swagger-ui/** 경로는 인터셉터 적용 제외
    }
}
