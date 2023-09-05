package com.fun.uncle.springboot2020.ffmpeg.simple;

import com.fun.uncle.springboot2020.utils.DownloadUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description: 云端链接文件来进行视频合成
 * @Author: summer
 * @CreateDate: 2023/9/5 11:43
 * @Version: 1.0.0
 */
public class UrlVideoConcatenation {

    public static void main(String[] args) {
        String input1Url = "https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/dev/summer/x.mp4";
        String input2Url = "https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/dev/summer/y.mp4";
        // 在当前项目库下，也即是同src一级
        String output = "out2.mp4";

        try {
            File input1File = DownloadUtil.downloadFile(input1Url);
            File input2File = DownloadUtil.downloadFile(input2Url);

            concatenateVideos(input1File.getAbsolutePath(), input2File.getAbsolutePath(), output);

            // Clean up downloaded files if needed
            input1File.delete();
            input2File.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void concatenateVideos(String input1, String input2, String output) {
        try {
            String command = "/usr/local/bin/ffmpeg -y -i " + input1 + " -i " + input2 +
                    " -filter_complex \"[0:v][0:a][1:v][1:a]concat=n=2:v=1:a=1[v][a]\" -map \"[v]\" -map \"[a]\" -c:v libx264 -crf 8 -preset veryfast " + output;

            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
            processBuilder.redirectErrorStream(true); // Redirect error stream to input stream
            Process process = processBuilder.start();

            // Capture and print command line output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();

            System.out.println("Video concatenation complete.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Video concatenation failed.");
        }
    }
}
