package com.fun.uncle.springboot2020.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
public class HelloController {

    @GetMapping
    @ApiOperation("Hello Spring Boot 方法")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isNotBlank(name)) {
            return name + " Hello Spring Boot";
        }
        return "Hello Spring Boot";
    }
}
