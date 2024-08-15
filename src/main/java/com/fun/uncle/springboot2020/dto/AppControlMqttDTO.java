package com.fun.uncle.springboot2020.dto;

import lombok.Data;

@Data
public class AppControlMqttDTO {

    private Long msgTimestamp;

    private MqttBody body;

    @Data
    public static class MqttBody<T> {

        private String msgType;

        private T data;

    }
}
