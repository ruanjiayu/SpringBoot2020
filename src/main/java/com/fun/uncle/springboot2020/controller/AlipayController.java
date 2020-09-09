package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.vo.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 支付宝支付
 * @Author: Summer
 * @DateTime: 2020/9/8 4:16 下午
 * @Version: 0.0.1-SNAPSHOT
 */

@RestController
@RequestMapping(value = "alipay")
public class AlipayController {


    @PostMapping(value = "pay")
    public CommonResult<String> pay() {

        return CommonResult.sucess();
    }
}
