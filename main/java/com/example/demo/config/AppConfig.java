package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/login2")
                .excludePathPatterns("/login/reg")
                .excludePathPatterns("/login.html") // 不拦截登录页面
                .excludePathPatterns("/regin.html") // 不拦截注册页面
                .excludePathPatterns("/**/**.html")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/reg_success.html")
                .excludePathPatterns("/reg_err.html")
                .excludePathPatterns("/**/*.png");
    }
}
