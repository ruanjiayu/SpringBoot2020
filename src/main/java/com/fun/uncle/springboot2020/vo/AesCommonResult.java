package com.fun.uncle.springboot2020.vo;

import com.fun.uncle.springboot2020.constant.enums.MessageEnum;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/8/1 5:01 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class AesCommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public static AesCommonResult sucess() {
        return sucess(null);
    }

    public static AesCommonResult sucess(Object data) {
        AesCommonResult commonResult = new AesCommonResult();
        commonResult.setMessage(MessageEnum.SUCCESS.getMessage());
        commonResult.setCode(MessageEnum.SUCCESS.getCode());
        commonResult.setData(data);
        return commonResult;
    }

    public static AesCommonResult error(Integer code, String message) {
        AesCommonResult commonResult = new AesCommonResult();
        commonResult.setMessage(message);
        commonResult.setCode(code);
        return commonResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
