package com.fun.uncle.springboot2020.mqtt;

import com.fun.uncle.springboot2020.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 15:06
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class MqttMessageHandle  implements MessageHandler {

    private final static Logger common_info = LoggerUtil.COMMON_INFO;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        LoggerUtil.info(common_info, message);
        MessageHeaders headers = message.getHeaders();
        //获取消息Topic
        String receivedTopic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);
        log.info("[获取到的消息的topic :]{} ", receivedTopic);
        //获取消息体
        String payload = (String) message.getPayload();
        log.info("[获取到的消息的payload :]{} ", payload);
    }
}
