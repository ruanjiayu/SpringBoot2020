package com.fun.uncle.springboot2020.mqtt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fun.uncle.springboot2020.mqtt.handle") // 指定包路径
public class MqttHandlersAutoConfiguration {

}