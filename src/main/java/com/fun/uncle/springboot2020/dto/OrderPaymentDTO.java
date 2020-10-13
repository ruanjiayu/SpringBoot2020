package com.fun.uncle.springboot2020.dto;

import com.fun.uncle.springboot2020.dto.base.PaymentDTO;

/**
 * @Description: 订单支付的DTO
 * @Author: Summer
 * @DateTime: 2020/9/8 4:23 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class OrderPaymentDTO extends PaymentDTO {

    // 等级
    private Integer level;

    // 是否微信环境
    private boolean wechatEnv;

    // 版本
    private String version;

    // 商品类型
    private Integer goodsType;

    // openId
    private String openId;

    // 是否记录订单
    private boolean orderRecord;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isWechatEnv() {
        return wechatEnv;
    }

    public void setWechatEnv(boolean wechatEnv) {
        this.wechatEnv = wechatEnv;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(boolean orderRecord) {
        this.orderRecord = orderRecord;
    }
}
