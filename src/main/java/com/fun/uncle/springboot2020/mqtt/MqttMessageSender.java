package com.fun.uncle.springboot2020.mqtt;

import com.fun.uncle.springboot2020.utils.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/30 09:47
 * @Version: 1.0.0
 */
@Component
public class MqttMessageSender {

    @Resource
    private MqttGateway mqttGateway;

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    /**
     * @param topic
     * @param data
     */
    public void sendToMqtt(String topic, String data) {
        LoggerUtil.info(common_info, "发送消息", topic, data);
        mqttGateway.sendToMqtt(topic, data);
    }

    /**
     * 发送包含qos的消息
     *
     * @param topic 主题
     * @param qos   对消息处理的几种机制。
     *              * 0 表示的是订阅者没收到消息不会再次发送，消息会丢失。<br>
     *              * 1 表示的是会尝试重试，一直到接收到消息，但这种情况可能导致订阅者收到多次重复消息。<br>
     *              * 2 多了一次去重的动作，确保订阅者收到的消息有一次。
     * @param data  消息体
     * @return void
     */
    public void sendToMqtt(String topic, Integer qos, String data) {
        LoggerUtil.info(common_info, "发送消息Qos", qos, topic, data);
        mqttGateway.sendToMqtt(topic, qos, data);
    }
}
