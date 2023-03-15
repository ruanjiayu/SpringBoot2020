package com.fun.uncle.springboot2020.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 15:35
 * @Version: 1.0.0
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);

    /**
     * 发送包含qos的消息
     * @param topic 主题
     * @param qos 对消息处理的几种机制。
     *          * 0 表示的是订阅者没收到消息不会再次发送，消息会丢失。<br>
     *          * 1 表示的是会尝试重试，一直到接收到消息，但这种情况可能导致订阅者收到多次重复消息。<br>
     *          * 2 多了一次去重的动作，确保订阅者收到的消息有一次。
     * @param data 消息体
     * @return void
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) Integer qos, String data);
}
