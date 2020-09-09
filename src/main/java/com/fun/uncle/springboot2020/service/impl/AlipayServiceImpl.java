package com.fun.uncle.springboot2020.service.impl;


import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.fun.uncle.springboot2020.constant.PayConstant;
import com.fun.uncle.springboot2020.dto.OrderPaymentDTO;
import com.fun.uncle.springboot2020.service.AlipayService;
import com.fun.uncle.springboot2020.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * @Description: 支付宝服务层
 * @Author: Summer
 * @DateTime: 2020/9/8 4:20 下午
 * @Version: 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class AlipayServiceImpl extends BasePayService implements AlipayService {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("############0.00");

    @Override
    public String tradePay(OrderPaymentDTO payment) {
        return super.tradePay(payment);
    }


    @Override
    protected void doCheck(OrderPaymentDTO payment) {
        // 暂无校验
    }

    @Override
    public void getPayAccount(OrderPaymentDTO payment, Map<String, Object> context) {
        // 得到支付账户 --- 从数据库内得到相关收款账户


        // 将收款人放入上下文
        // context.put(PayConstant.PAY_ACCOUNT, payAccount);
        String key = PayConstant.PAY_ACCOUNT;
    }

    @Override
    protected void buildOrderRecord(OrderPaymentDTO payment, Map<String, Object> context) {
        // 目前定位未明确
        // 判断是否存在交易记录
        if (!payment.isOrderRecord()) {
            return;
        }
        // 将订单记录放入上下文 context.put(key, orderRecord);
        String key = PayConstant.ORDER_RECORD;
    }

    @Override
    public void buildPayModel(OrderPaymentDTO payment, Map<String, Object> context) {
        // 目前来看是将用户的资金进行冻结，然后再进行支付
        // 组装支付模型
        AlipayTradePayModel payModel = new AlipayTradePayModel();
        String totalAmount =  DECIMAL_FORMAT.format(payment.getOrderAmount() / 100F);
        payModel.setTotalAmount(totalAmount);
        // 避免特殊字符
        String goodsInfo = CodeUtil.encode(payment.getGoodsInfo());
        payModel.setBody(goodsInfo);
        payModel.setSubject(goodsInfo);
        payModel.setOutTradeNo(payment.getOrderNo());
        payModel.setTimeoutExpress(payment.getTimeoutExpress() + "m");
        payModel.setProductCode(PayConstant.QUICK_MSECURITY_PAY);
        context.put(PayConstant.PAY_MODEL, payModel);
    }

    @Override
    public String doPay(OrderPaymentDTO payment, Map<String, Object> context) {
        // 得到收款的账户信息
        context.get(PayConstant.PAY_ACCOUNT);
        // 构建支付宝客户端相关信息 AlipayClient alipayClient
        AlipayTradePayModel payModel = (AlipayTradePayModel) context.get(PayConstant.PAY_MODEL);
        // 开始支付
        AlipayTradeAppPayRequest appPayRequest = new AlipayTradeAppPayRequest();
        appPayRequest.setBizModel(payModel);
//        appPayRequest.setNotifyUrl(payNotifyBaseUrl + accountDTO.getNotifyUrlPath());
//        payResponse = alipayClient.sdkExecute(appPayRequest);
        log.info("【支付宝支付】alipay订单支付end...", payment.getOrderNo());
        return "支付";
    }


}
