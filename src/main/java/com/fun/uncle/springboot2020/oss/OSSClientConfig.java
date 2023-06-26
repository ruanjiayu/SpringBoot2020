package com.fun.uncle.springboot2020.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author huanghaifeng
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSClientConfig {

    /**
     * 阿里云OSS的 accessKey
     */
    public String accessKey;

    /**
     * 阿里云OSS的 secret
     */
    public String secret;

    /**
     * 角色
     */
    public String roleArn;

    /**
     * 角色名
     */
    public String roleSessionName;

    /**
     * endpoint
     */
    public String endpoint;

    /**
     * bucketName 公共读的bucketName
     */
    public String bucketName;

    /**
     * bucketName 公共读的bucketName
     */
    public String privateBucketName;

    /**
     * 初始化课程资料oss客户端
     *
     * @return oss客户端
     */
    @Primary
    @Bean
    public OSS initOssClient() {
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setSupportCname(true);
        return new OSSClientBuilder().build("oss-cn-hangzhou.aliyuncs.com", accessKey, secret, conf);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public String getRoleSessionName() {
        return roleSessionName;
    }

    public void setRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPrivateBucketName() {
        return privateBucketName;
    }

    public void setPrivateBucketName(String privateBucketName) {
        this.privateBucketName = privateBucketName;
    }
}
