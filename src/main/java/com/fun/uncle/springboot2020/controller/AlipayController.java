package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.utils.LoggerUtil;
import com.fun.uncle.springboot2020.vo.CommonResult;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    @GetMapping(value = "pay")
    public CommonResult<String> pay(@RequestParam(value = "name", defaultValue = "ruanjiayu") String name) {
        LoggerUtil.info(common_info, name);
        return CommonResult.sucess();
    }
}
