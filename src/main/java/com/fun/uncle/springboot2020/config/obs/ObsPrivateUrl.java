package com.fun.uncle.springboot2020.config.obs;

import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;

import java.io.IOException;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/2 11:11
 * @Version: 1.0.0
 */
public class ObsPrivateUrl {


    private static final String endPoint = "#endpoint#";
    private static final String ak = "#永久的AK#";
    private static final String sk = "#永久的SK#";

    public static final String BUCKET_NAME = "#自己的bucket#";


    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        // URL有效期，3600秒
        long expireSeconds = 3600L;

        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(BUCKET_NAME);
        request.setObjectKey("2023/0608/52108457/75a2d3041fc34daa81c2e4dbb98cfed4_37e490af048042ddaf45-ba4c86.jpg");

        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
        //获取支持图片转码的下载链接
        System.out.println("Getting object using temporary signature url:");
        System.out.println("\t" + response.getSignedUrl());

        obsClient.close();
    }
}
