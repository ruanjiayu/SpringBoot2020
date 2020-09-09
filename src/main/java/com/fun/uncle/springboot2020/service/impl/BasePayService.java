package com.fun.uncle.springboot2020.service.impl;

import com.fun.uncle.springboot2020.dto.OrderPaymentDTO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 基础支付 模版模式
 * @Author: Summer
 * @DateTime: 2020/9/8 4:31 下午
 * @Version: 0.0.1-SNAPSHOT
 */

@Component
@Slf4j
public class BasePayService {


    protected String tradePay(OrderPaymentDTO payment) {
        log.info(payment.getPayWay() + " start...", payment);
        // 初始化上下文数据
        Map<String, Object> context = Maps.newHashMap();

        // 校验参数
        doCheck(payment);

        // 获取支付账户
        getPayAccount(payment, context);

        // 构建支付记录
        buildOrderRecord(payment, context);

        // 构建支付数据模型
        buildPayModel(payment, context);

        // 数据库记录
        recordDB(payment, context);

        // 开始支付
        return doPay(payment, context);
    }


    protected void doCheck(OrderPaymentDTO payment) {
        throw new UnsupportedOperationException();
    }

    protected void getPayAccount(OrderPaymentDTO payment, Map<String, Object> context) {
        throw new UnsupportedOperationException();
    }

    protected void buildOrderRecord(OrderPaymentDTO payment, Map<String, Object> context) {
        throw new UnsupportedOperationException();
    }

    protected void buildPayModel(OrderPaymentDTO payment, Map<String, Object> context) {
        throw new UnsupportedOperationException();
    }

    private void recordDB(OrderPaymentDTO payment, Map<String, Object> context) {
        // 创建交易记录 -- 设定状态为支付中

        // 流水记录交易记录
    }

    protected String doPay(OrderPaymentDTO payment, Map<String, Object> context) {
        throw new UnsupportedOperationException();
    }
}
