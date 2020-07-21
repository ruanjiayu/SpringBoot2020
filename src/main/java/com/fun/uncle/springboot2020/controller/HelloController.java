package com.fun.uncle.springboot2020.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 11:14 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@RestController
@Api(tags = "hello 控制层")
@Slf4j
public class HelloController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    @ApiOperation("Hello Spring Boot 方法")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        log.info("hello");
        if (StringUtils.isNotBlank(name)) {
            stringRedisTemplate.opsForValue().set("hello", name);
            return name + " Hello Spring Boot";
        }
        name = "徐帆";
         if (StringUtils.isNotBlank(stringRedisTemplate.opsForValue().get("hello"))) {
             name = stringRedisTemplate.opsForValue().get("hello");
         }
        return   name + "Hello Spring Boot";
    }
}
