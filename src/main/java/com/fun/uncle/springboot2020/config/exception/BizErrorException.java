package com.fun.uncle.springboot2020.config.exception;

import com.fun.uncle.springboot2020.constant.enums.MessageEnum;
import com.fun.uncle.springboot2020.vo.CommonResult;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/8/1 5:25 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class BizErrorException extends RuntimeException {

    private static final long serialVersionUID = 2347607651264744177L;

    private Integer code;

    public BizErrorException(Integer code) {
        this.code = code;
    }

    public BizErrorException(String message) {
        super(message);
        this.code = code;
    }

    public BizErrorException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BizErrorException(MessageEnum error) {
        super(error.getMessage());
        this.code = error.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

