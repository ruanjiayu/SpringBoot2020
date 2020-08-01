package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.config.exception.ApiException;
import com.fun.uncle.springboot2020.constant.enums.MessageEnum;
import com.fun.uncle.springboot2020.utils.Assert;
import com.fun.uncle.springboot2020.vo.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/8/1 5:08 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Api(tags = "异常处理类")
@RestController
@RequestMapping(value = "exception")
public class ExceptionController {

    @GetMapping("/api")
    public CommonResult apiException() {
        throw new ApiException(MessageEnum.ERROR);
    }

    @GetMapping("/runtime")
    public CommonResult runtimeException() {
        throw new RuntimeException();
    }

    @GetMapping("/bizError")
    public CommonResult bizErrorException() {
        Assert.isTrue(false, "【我出现异常了】");
        return CommonResult.sucess();
    }

}
