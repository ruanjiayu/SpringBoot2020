package com.fun.uncle.springboot2020.service;

import com.fun.uncle.springboot2020.dto.OrderPaymentDTO;

/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/9/8 4:19 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public interface AlipayService {

    String tradePay(OrderPaymentDTO payment);
}
