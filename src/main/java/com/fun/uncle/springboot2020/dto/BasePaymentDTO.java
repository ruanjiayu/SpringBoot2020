package com.fun.uncle.springboot2020.dto;

/**
 * @Description: 基础支付
 * @Author: Summer
 * @DateTime: 2020/9/8 4:22 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class BasePaymentDTO {

    // 用户ID
    private Long userId;

    // 金额
    private Long orderAmount;

    // 订单编号
    private String orderNo;

    // 订单类型
    private Integer orderType;

    // 商品个数
    private Integer goodsNum;

    // 商品名称
    private String goodsInfo;

    // 支付方式
    private Integer payWay;

    // 平台
    private String platform;

    // 支付超时时间
    private Integer timeoutExpress;

    // 支付场景PaySceneEnum
    private String payScene;

    // 交易类型
    private String tradeType;

    // 有数-交易类型
    private String youthsType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(Integer timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getPayScene() {
        return payScene;
    }

    public void setPayScene(String payScene) {
        this.payScene = payScene;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getYouthsType() {
        return youthsType;
    }

    public void setYouthsType(String youthsType) {
        this.youthsType = youthsType;
    }
}
