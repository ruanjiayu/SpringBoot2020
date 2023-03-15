package com.fun.uncle.springboot2020.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池配置
 * @Author: summer
 * @CreateDate: 2023/1/30 17:50
 * @Version: 1.0.0
 */
@Configuration
public class ThreadPoolConfig {


    @Bean("commonThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getCommonThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        threadPoolTaskExecutor.setThreadGroupName("commonThreadPool");
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(300);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean("mqttThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getTaskThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setKeepAliveSeconds(300);
        executor.setThreadGroupName("mqttThreadPool");
        executor.setMaxPoolSize(300);
        executor.setQueueCapacity(1000);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return executor;
    }


}
