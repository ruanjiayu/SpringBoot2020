package com.fun.uncle.springboot2020.obs;

import com.fun.uncle.springboot2020.constant.enums.DateTimeFormatterEnum;
import com.fun.uncle.springboot2020.utils.LocalDateTimeUtil;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/2 09:17
 * @Version: 1.0.0
 */
public class ObsInit {

    private static final String endPoint = "#endpoint#";
    private static final String ak = "#永久的AK#";
    private static final String sk = "#永久的SK#";

    public static final String BUCKET_NAME = "#自己的bucket#";


    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        String fileName = "xxx.txt";
        String filePath = LocalDateTimeUtil.format(LocalDateTime.now(), DateTimeFormatterEnum.FORMAT_Y_M_D_BIAS) + "/" + fileName;
        // 上传文件
        obsClient.putObject(BUCKET_NAME, filePath, new ByteArrayInputStream("Hello OBS".getBytes()));
//        obsClient.createGetTemporarySignature()

        // URL有效期，3600秒
        long expireSeconds = 3600L;

        Map<String, String> headers = new HashMap<>();
        String contentType = "text/plain";
        headers.put("Content-Type", contentType);

        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.PUT, expireSeconds);
        request.setBucketName(BUCKET_NAME);
        request.setObjectKey("test1");
        request.setHeaders(headers);

        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);

        System.out.println("Creating object using temporary signature url:");
        System.out.println("\t" + response.getSignedUrl());

        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> entry : response.getActualSignedRequestHeaders().entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        //使用PUT请求上传对象
        Request httpRequest = builder.url(response.getSignedUrl()).put(RequestBody.create(MediaType.parse(contentType), "Hello yaya".getBytes("UTF-8"))).build();
        OkHttpClient httpClient = new OkHttpClient.Builder().followRedirects(false).retryOnConnectionFailure(false)
                .cache(null).build();

        Call c = httpClient.newCall(httpRequest);
        Response res = c.execute();
        System.out.println("\tStatus:" + res.code());
        if (res.body() != null) {
            System.out.println("\tContent:" + res.body().string() + "\n");
        }


        obsClient.close();
    }

}
