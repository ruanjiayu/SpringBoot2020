package com.fun.uncle.springboot2020.config.im;

import io.github.doocs.im.ClientConfiguration;
import io.github.doocs.im.ImClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2022/10/12 19:53
 * @Version: 1.0.0
 */
@Configuration
public class IMConfiguration {


    private Long sdkAppId = 1400704371L;

    private String userId = "administrator";

    private String key = "a176161f08c1a1175363ab8ec9971e4b45ab4312e802c494bbb002e871bf1938";

    @Bean(name = "ImClient")
    public ImClient getImClient() {
        // 默认配置
        ClientConfiguration config = new ClientConfiguration();
        // 最大重试次数
        config.setMaxRetries(3);
        // HTTP 连接超时（毫秒）
        config.setConnectTimeout(10_000);
        // HTTP 读超时（毫秒）
        config.setReadTimeout(10_000);
        // HTTP 写超时（毫秒）
        config.setWriteTimeout(10_000);
        // 一个完整的 HTTP 调用的时间限制。这包括解析 DNS、连接、写入请求正文、服务器处理以及读取响应正文。（毫秒）
        config.setCallTimeout(20_000);
        // UserSig 签名有效时长（秒）
        config.setExpireTime(86400);
        // 是否自动进行 UserSig 签名续期
        config.setAutoRenewSig(true);

        return new ImClient(sdkAppId, userId, key, config);
    }



}
