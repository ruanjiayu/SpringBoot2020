package com.fun.uncle.springboot2020.controller;

import com.fun.uncle.springboot2020.mqtt.MqttGateway;
import com.fun.uncle.springboot2020.request.mqtt.MqttMessageRequst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 15:39
 * @Version: 1.0.0
 */
@Api(tags = "mqtt")
@RestController
@RequestMapping(value = "mqtt")
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @ApiOperation("mqtt消息推送")
    @PostMapping(value = "/push")
    public String hello(@RequestBody MqttMessageRequst requst){
        mqttGateway.sendToMqtt(requst.getTopic(), requst.getData());
        return "success";
    }

}
