package com.fun.uncle.springboot2020.constant.enums;

/**
 * @Description: 返回的错误的消息
 * @Author: Summer
 * @DateTime: 2020/8/1 4:59 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public enum MessageEnum {
    UNKNOW_ERROR(-1, "未知错误！"),
    ERROR(5000, "系统错误"),
    SUCCESS(200, "操作成功！"),
    ;
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
