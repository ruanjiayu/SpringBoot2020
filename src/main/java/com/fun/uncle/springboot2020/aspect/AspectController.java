package com.fun.uncle.springboot2020.aspect;

import com.fun.uncle.springboot2020.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/8/2 2:35 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/aspect")
public class AspectController {

    @GetMapping
    public CommonResult aspect(String message) {
        log.info("aspect controller");
        return CommonResult.sucess(message);
    }


    @GetMapping("/exception")
    public CommonResult exception() {
        throw new RuntimeException("runtime exception");
    }

    @GetMapping("/sleep/{time}")
    public CommonResult sleep(@PathVariable("time") long time) {
        log.info("sleep");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("error",e);
        }
        if (time == 1000) {
            throw new RuntimeException("runtime exception");
        }
        log.info("wake up");
        return CommonResult.sucess("wake up");
    }
}
