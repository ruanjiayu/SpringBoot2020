package com.fun.uncle.springboot2020.constant.enums;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/9/8 4:49 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public enum TradeOrderStatusEnum {

    CREATE_ORDER(1, "订单创建"),

    PAYING(2, "支付中"),

    PAYMENT_SUCCESS(3, "支付成功"),

    FAIL(4, "失败"),

    CLOSED(5, "关闭"),

    REFUND(6, "退款");

    private Integer code;

    private String name;

    TradeOrderStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
