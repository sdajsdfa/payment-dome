package com.yhgc.api.config;

import com.yhgc.api.Interceptor.LoginInterceptor;
import com.yhgc.api.Interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 
@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public UserLoginInterceptor UserLoginInterceptor() {
        return new UserLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(UserLoginInterceptor())
                .addPathPatterns("/**") // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .excludePathPatterns(
                        "/appLogin",
                        "/assets/**/**/**",
                        "/login",
                         "/serverlist/queryAllServerlist",
                         "/serverlist/downloadFromJc"
                );

    }
}