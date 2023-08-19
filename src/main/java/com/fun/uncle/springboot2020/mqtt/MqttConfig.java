package com.fun.uncle.springboot2020.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 15:01
 * @Version: 1.0.0
 */
@Configuration
@Import(MqttHandlersAutoConfiguration.class) // 导入配置类
public class MqttConfig {

    /**
     * 以下属性将在配置文件中读取
     **/
    @Autowired
    private MqttProperties mqttProperties;

    @Qualifier(value = "mqttThreadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor mqttThreadPoolTaskExecutor;

    @Autowired
    private RouterMessageHandler routerMessageHandler;

    @Autowired
    private ApplicationContext applicationContext;


    //Mqtt 客户端工厂
    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        //如果设置为 false，客户端和服务器将在客户端、服务器和连接重新启动时保持状态。随着状态的保持：
        //  即使客户端、服务器或连接重新启动，消息传递也将可靠地满足指定的 QOS。服务器将订阅视为持久的。
        // 如果设置为 true，客户端和服务器将不会在客户端、服务器或连接重新启动时保持状态。
        options.setCleanSession(true);
        //该值以秒为单位，必须>0，定义了客户端等待与 MQTT 服务器建立网络连接的最大时间间隔。
        // 默认超时为 30 秒。值 0 禁用超时处理，这意味着客户端将等待直到网络连接成功或失败。
        options.setConnectionTimeout(30);
        //此值以秒为单位，定义发送或接收消息之间的最大时间间隔，必须>0
        options.setKeepAliveInterval(60);
        //自动重新连接
        options.setAutomaticReconnect(true);
        options.setServerURIs(mqttProperties.getHostUrl().split(","));
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }

    // Mqtt 管道适配器
    @Bean
    public MqttPahoMessageDrivenChannelAdapter adapter(MqttPahoClientFactory factory) {
        return new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId(), factory, mqttProperties.getSubscribeTopics().toArray(new String[0]));
    }


    // 消息消费者
    @Bean
    public MessageProducer mqttInbound(MqttPahoMessageDrivenChannelAdapter adapter) {
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        //入站投递的通道
        adapter.setOutputChannel(mqttInboundChannel());
        adapter.setQos(0);
        return adapter;
    }


    // 出站处理器
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(MqttPahoClientFactory factory) {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(mqttProperties.getOutClientId(), factory);
        handler.setAsync(true);
        handler.setConverter(new DefaultPahoMessageConverter());
        return handler;
    }

    @Bean
    public static RouterMessageHandler createRouterMessageHandler(ApplicationContext applicationContext, MqttProperties mqttProperties) {
        RouterMessageHandler router = new RouterMessageHandler();
        Map<String, String> map = mqttProperties.getHandleTopic();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            router.addTopicHandler(entry.getValue(), applicationContext.getBean(entry.getKey(), MessageHandler.class));
        }

//        router.addTopicHandler("dev/a", t1MessageHandle);
//        router.addTopicHandler("dev/b", t2MessageHandle);
        return router;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInboundChannel")
    public RouterMessageHandler routerMessageHandler() {
        return createRouterMessageHandler(applicationContext, mqttProperties);
    }

    //出站消息管道
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new ExecutorChannel(mqttThreadPoolTaskExecutor);
    }


    // 入站消息管道
    @Bean
    public MessageChannel mqttInboundChannel() {
        return new ExecutorChannel(mqttThreadPoolTaskExecutor);
    }

}
