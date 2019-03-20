package org.slam.config;

import org.slam.interceptor.NoticeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final NoticeInterceptor noticeInterceptor;

    public WebMvcConfig(NoticeInterceptor noticeInterceptor) {
        this.noticeInterceptor = noticeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noticeInterceptor).excludePathPatterns("/sign-in", "/sign-up", "/js/**", "/css/**", "/image/**");
    }

}
