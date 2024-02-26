package com.fun.uncle.springboot2020.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: mvc 控制器
 * @Author: Summer
 * @DateTime: 2020/7/20 2:45 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 类似与栈的效果，先进后出
     * 正向迭代 org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 逆向迭代 org.springframework.web.servlet.HandlerExecutionChain#applyPreHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 逆向迭代 org.springframework.web.servlet.HandlerExecutionChain#applyPostHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.web.servlet.ModelAndView)
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(timeInterceptor);
    }

    /**
     * 当客户端请求资源时，服务器将默认返回JSON格式的数据。
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

}
