package com.fun.uncle.springboot2020.mqtt;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.HashMap;
import java.util.Map;

public class RouterMessageHandler implements MessageHandler {

    private final Map<String, MessageHandler> topicHandlers = new HashMap<>();

    public void addTopicHandler(String topic, MessageHandler handler) {
        topicHandlers.put(topic, handler);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = extractTopicFromMessage(message);
        MessageHandler handler = topicHandlers.get(topic);
        if (handler != null) {
            handler.handleMessage(message);
        }
    }

    private String extractTopicFromMessage(Message<?> message) {
        // 从消息中提取 topic 的逻辑
        String receivedTopic = message.getHeaders().get("mqtt_receivedTopic", String.class);
        return this.getCommonTopic(receivedTopic);
    }

    private String getCommonTopic(String str) {
        int lastSlashIndex = str.lastIndexOf("/");
        if (lastSlashIndex != -1) {
            return str.substring(0, lastSlashIndex);
        } else {
            return str;
        }
    }
}
