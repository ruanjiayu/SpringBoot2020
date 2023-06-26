package com.fun.uncle.springboot2020.obs;

import com.fun.uncle.springboot2020.constant.enums.DateTimeFormatterEnum;
import com.fun.uncle.springboot2020.utils.LocalDateTimeUtil;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/5 17:43
 * @Version: 1.0.0
 */
public class ObsTempSignedUrl {

    private static final String endPoint = "#endpoint#";
    private static final String ak = "#永久的AK#";
    private static final String sk = "#永久的SK#";

    public static final String BUCKET_NAME = "#自己的bucket#";

    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        String fileName = "xxx.txt";
        String filePath = LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnum.FORMAT_Y_M_D_BIAS) + "/" + fileName;


        // URL有效期，3600秒
        long expireSeconds = 3600L;

        Map<String, String> headers = new HashMap<>();
        String contentType = "text/plain";
        headers.put("Content-Type", contentType);

        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.PUT, expireSeconds);
        request.setBucketName(BUCKET_NAME);
        request.setObjectKey(filePath);
        request.setHeaders(headers);

        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);

        System.out.println("Creating object using temporary signature url:");
        System.out.println(response.getSignedUrl());

        obsClient.close();
    }

}
