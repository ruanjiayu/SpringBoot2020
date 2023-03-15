package com.fun.uncle.springboot2020.request.mqtt;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 16:35
 * @Version: 1.0.0
 */
public class MqttMessageRequst {

    private String topic;

    private String data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
