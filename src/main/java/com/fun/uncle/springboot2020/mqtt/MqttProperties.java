package com.fun.uncle.springboot2020.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/1/29 14:59
 * @Version: 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接地址
     */
    private String hostUrl;

    /**
     * 进-客户Id
     */
    private String inClientId;

    /**
     * 出-客户Id
     */
    private String outClientId;

    /**
     * 客户Id
     */
    private String clientId;

    /**
     * 默认连接话题
     */
    private List<String> subscribeTopics;

    private Map<String, String> handleTopic;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 保持连接数
     */
    private int keepalive;

    /**是否清除session*/
    private boolean clearSession;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getInClientId() {
        return inClientId;
    }

    public void setInClientId(String inClientId) {
        this.inClientId = inClientId;
    }

    public String getOutClientId() {
        return outClientId;
    }

    public void setOutClientId(String outClientId) {
        this.outClientId = outClientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getSubscribeTopics() {
        return subscribeTopics;
    }

    public void setSubscribeTopics(List<String> subscribeTopics) {
        this.subscribeTopics = subscribeTopics;
    }

    public Map<String, String> getHandleTopic() {
        return handleTopic;
    }

    public void setHandleTopic(Map<String, String> handleTopic) {
        this.handleTopic = handleTopic;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    public boolean isClearSession() {
        return clearSession;
    }

    public void setClearSession(boolean clearSession) {
        this.clearSession = clearSession;
    }
}
