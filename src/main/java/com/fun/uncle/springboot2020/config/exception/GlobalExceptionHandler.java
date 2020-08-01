package com.fun.uncle.springboot2020.config.exception;

import com.fun.uncle.springboot2020.constant.enums.MessageEnum;
import com.fun.uncle.springboot2020.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/8/1 4:55 下午
 * @Version: 0.0.1-SNAPSHOT
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handle(Exception e) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(MessageEnum.UNKNOW_ERROR.getCode());
        commonResult.setMessage(e.getMessage() == null ? MessageEnum.UNKNOW_ERROR.getMessage() : e.getMessage());
        log.error(e.getMessage(),e);
        return commonResult;
    }

    @ExceptionHandler(ApiException.class)
    public CommonResult handle(ApiException e) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(e.getCode());
        commonResult.setMessage(e.getMessage());
        log.error(e.getMessage(),e);
        return commonResult;
    }

    @ExceptionHandler(BizErrorException.class)
    public CommonResult handle(BizErrorException e) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(e.getCode());
        commonResult.setMessage(e.getMessage());
        log.error(e.getMessage(),e);
        return commonResult;
    }

}
