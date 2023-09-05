package com.fun.uncle.springboot2020.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: 下载工具类
 * @Author: summer
 * @CreateDate: 2023/8/23 14:31
 * @Version: 1.0.0
 */
public class DownloadUtil {

    public static void main(String[] args) {
        String url = "https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/dev/summer/x.mp4";
        String fileName = getFileNameFromURL(url);
        System.out.println("File name: " + fileName);
    }

    public static File downloadFile(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to download file: " + response);
        }

        String fileName = getFileNameFromURL(url);
        File outputFile = new File(fileName);
        try (BufferedSink sink = Okio.buffer(Okio.sink(outputFile))) {
            sink.writeAll(response.body().source());
        }

        return outputFile;
    }


    public static String getFileNameFromURL(String url) {
        try {
            URL urlObject = new URL(url);
            String path = urlObject.getPath();
            return path.substring(path.lastIndexOf("/") + 1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
