package com.fun.uncle.springboot2020.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 2:45 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }
}
