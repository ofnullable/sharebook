package org.slam.publicshare.config;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.interceptor.NoticeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final NoticeInterceptor noticeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noticeInterceptor)
                .excludePathPatterns("/auth/sign-in", "/auth/sign-up", "/js/**", "/css/**", "/image/**");
    }

}