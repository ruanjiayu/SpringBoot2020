package com.fun.uncle.springboot2020;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
*
* @Description: 对于Spring boot的相关学习
* @Author: Summer
* @DateTime: 2020/7/20 11:13 上午
* @Version: 0.0.1-SNAPSHOT
*/
@SpringBootApplication
@MapperScan("com.fun.uncle.springboot2020.mapper")
@EnableScheduling
public class SpringBoot2020Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2020Application.class, args);
    }

}
