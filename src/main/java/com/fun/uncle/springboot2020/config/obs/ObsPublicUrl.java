package com.fun.uncle.springboot2020.config.obs;

import com.obs.services.ObsClient;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/6 09:56
 * @Version: 1.0.0
 */
public class ObsPublicUrl {

    private static final String endPoint = "#endpoint#";
    private static final String ak = "#永久的AK#";
    private static final String sk = "#永久的SK#";

    public static final String BUCKET_NAME = "#自己的bucket#";


    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        String filePath = "public/.png";

        /*方法一*/
//        PutObjectResult result = obsClient.putObject(BUCKET_NAME, filePath, new ByteArrayInputStream("Hello 1".getBytes()));

        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName(BUCKET_NAME);
        request.setObjectKey(filePath);
        request.setFile(new File("/Users/xian/Desktop/" + filePath ));
        // 设置对象访问权限为公共读
        request.setAcl(AccessControlList.REST_CANNED_PRIVATE);
        PutObjectResult result = obsClient.putObject(request);

        System.out.println(result.getObjectUrl());

        obsClient.close();
    }

}
