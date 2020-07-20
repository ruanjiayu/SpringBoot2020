package com.fun.uncle.springboot2020.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 时间拦截器
 * @Author: Summer
 * @DateTime: 2020/7/20 2:58 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private ThreadLocal<Long> threadLocalStart = new ThreadLocal<>();
    private ThreadLocal<Long> threadLocalEnd = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        long startTIme = System.currentTimeMillis();
//        threadLocalStart.set(startTIme);
//        log.info("开始时间：{}", startTIme);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        long endTIme = System.currentTimeMillis();
//        threadLocalEnd.set(endTIme);
//        log.info("结束时间：{}", endTIme);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        long startTime = threadLocalStart.get();
//        long endTime = threadLocalEnd.get();
//        log.info("接口执行时间：{} 毫秒", endTime - startTime);
    }
}
