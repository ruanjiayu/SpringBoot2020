package com.fun.uncle.springboot2020.config.obs.sts;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/9 11:47
 * @Version: 1.0.0
 */
public class ObsUtil {

    private static final String endPoint = "#endpoint#";

    private static final String ak = "#临时的ak#";

    private static final String sk = "#临时的SK#";

    private static final String securitytoken = "#临时的token#";

    public static final String BUCKET_NAME = "#自己的bucket#";


    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, securitytoken, endPoint);

        String content = "Hello OBS";
        PutObjectResult result = obsClient.putObject(BUCKET_NAME, "text/20230626.text", new ByteArrayInputStream(content.getBytes()));

        System.out.println(result.getObjectUrl());

        obsClient.close();
    }
}
