package com.fun.uncle.springboot2020;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: 对于Spring boot的相关学习
 * @Author: Summer
 * @DateTime: 2020/7/20 11:13 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@SpringBootApplication
// 不扫描对应的文件夹，因为需要相关配置
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.fun.uncle.springboot2020.mqtt.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.fun.uncle.springboot2020.obs.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.fun.uncle.springboot2020.oss.*")

})

@MapperScan("com.fun.uncle.springboot2020.mapper")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
