package com.fun.uncle.springboot2020.obs;

import com.obs.services.ObsClient;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/5 17:49
 * @Version: 1.0.0
 */
public class ObsTempUpload {

    private static final String endPoint = "#endpoint#";
    private static final String ak = "#永久的AK#";
    private static final String sk = "#永久的SK#";

    public static final String BUCKET_NAME = "#自己的bucket#";

    public static void main(String[] args) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        // todo:: 填写你自己的
        String signUrl = "";


        Map<String, String> headers = new HashMap<>();
        String contentType = "text/plain";
//        headers.put("Content-Type", contentType);

        Request.Builder builder = new Request.Builder();
//        builder.header();

        //使用PUT请求上传对象
        Request httpRequest = builder.url(signUrl).put(RequestBody.create(MediaType.parse(contentType), "Hello ruanjiayu".getBytes("UTF-8"))).build();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .followRedirects(false)
                .retryOnConnectionFailure(false)
                .cache(null).build();

        Call c = httpClient.newCall(httpRequest);

        Response res = c.execute();
        System.out.println("Status:" + res.code());
        if (res.body() != null) {
            System.out.println("Content:" + res.body().string());
        }
        obsClient.close();
    }
}
