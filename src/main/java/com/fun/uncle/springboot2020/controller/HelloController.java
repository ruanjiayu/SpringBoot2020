package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.config.GetModelPathHandler;
import com.fun.uncle.springboot2020.request.HelloRequest;
import com.fun.uncle.springboot2020.utils.LoggerUtil;
import com.fun.uncle.springboot2020.vo.CommonResult;
import com.fun.uncle.springboot2020.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final static Logger biz_log = LoggerUtil.BIZ_LOG;

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private GetModelPathHandler getModelPathHandler;

    @GetMapping(value = "hello")
    @ApiOperation("Hello Spring Boot 方法")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        biz_log.info("biz hello");
        LoggerUtil.info(biz_log, "hhhlllo");
        common_info.info("hello");
//        if (StringUtils.isNotBlank(name)) {
//            stringRedisTemplate.opsForValue().set("hello", name);
//            return name + " Hello Spring Boot";
//        }
//        name = "徐帆";
//        if (StringUtils.isNotBlank(stringRedisTemplate.opsForValue().get("hello"))) {
//            name = stringRedisTemplate.opsForValue().get("hello");
//        }

        LoggerUtil.info(common_info, "本服务接口", getModelPathHandler.getAllAddTagAnnotationUrl());
        LoggerUtil.info(common_info, "注解和URL匹配", getModelPathHandler.getApiOperationToGetMappingMapping());

        return name + "Hello Spring Boot";
    }


    @PostMapping(value = "hello/post")
    @ApiOperation("Hello Spring Boot 方法")
    public CommonResult helloPost(@RequestBody HelloRequest request) {
        UserVO userVO = new UserVO();
        userVO.setId(request.getId());
        userVO.setNickname(request.getNickname());
        userVO.setAge(10);
        userVO.setAddress("绍兴皋埠");
        return CommonResult.sucess(userVO);
    }
}
