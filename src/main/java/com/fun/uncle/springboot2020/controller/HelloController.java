package com.fun.uncle.springboot2020.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 11:14 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@RestController
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello Spring Boot";
    }
}
