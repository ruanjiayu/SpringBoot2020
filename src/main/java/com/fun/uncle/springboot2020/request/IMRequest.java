package com.fun.uncle.springboot2020.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2022/11/8 17:50
 * @Version: 1.0.0
 */
public class IMRequest {

    @JsonProperty(value = "SdkAppid")
    private String sdkAppid;

    @JsonProperty(value = "CallbackCommand")
    private String callbackCommand;

    @JsonProperty(value = "ClientIP")
    private String clientIP;

    public String getSdkAppid() {
        return sdkAppid;
    }

    public void setSdkAppid(String sdkAppid) {
        this.sdkAppid = sdkAppid;
    }

    public String getCallbackCommand() {
        return callbackCommand;
    }

    public void setCallbackCommand(String callbackCommand) {
        this.callbackCommand = callbackCommand;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
