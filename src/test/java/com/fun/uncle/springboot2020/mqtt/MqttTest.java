package com.fun.uncle.springboot2020.mqtt;

import com.alibaba.fastjson.JSON;
import com.fun.uncle.springboot2020.dto.AppControlMqttDTO;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: List的测试
 * @Author: Summer
 * @DateTime: 2020/8/20 4:10 下午
 * @Version: 0.0.1-SNAPSHOT
 */

public class MqttTest {


    @Test
    void test() {
        AppControlMqttDTO.MqttBody mqttBody = new AppControlMqttDTO.MqttBody();
        mqttBody.setMsgType("haha");

        System.out.println(new Date());
        System.out.println(JSON.toJSONString(mqttBody));
    }


}
