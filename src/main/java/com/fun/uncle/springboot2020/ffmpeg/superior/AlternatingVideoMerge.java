package com.fun.uncle.springboot2020.ffmpeg.superior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 我有2个关于动作的视频文件A，B，
 * 我想要合并成交替出现视频文件，
 * 比如我要展示1次动作，那么只要A文件即可，
 * 我要展示2次动作，那么就是A文件拼接上B文件，
 * 我要展示3次动作，那么就是A文件拼接上B文件，再拼接上A文件，
 * 我要展示4次动作，那么就是A文件拼接上B文件，再拼接上A文件，再拼接上B文件
 */
public class AlternatingVideoMerge {

    public static void main(String[] args) {
        String inputA = "/Users/xian/Desktop/x.mp4";
        String inputB = "/Users/xian/Desktop/y.mp4";
        String outputDirectory = "/Users/xian/Desktop/";

//        int[] numIterations = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        int[] numIterations = {5};

        Long startTime = System.currentTimeMillis();
        for (int numIteration : numIterations) {
            String outputFilePath = outputDirectory + "output_" + numIteration + "_xn.mp4";
            generateAlternatingVideo(inputA, inputB, numIteration, outputFilePath);
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void generateAlternatingVideo(String inputA, String inputB, int numIteration, String output) {
        try {
            StringBuilder filterComplex = new StringBuilder();
            for (int i = 0; i < numIteration; i++) {
                filterComplex.append("[").append(i % 2 == 0 ? "0" : "1").append(":v][")
                        .append(i % 2 == 0 ? "0" : "1").append(":a]");
            }
            String command = "/usr/local/bin/ffmpeg -y -i " + inputA + " -i " + inputB +
                    " -filter_complex \"" + filterComplex + "concat=n=" + numIteration + ":v=1:a=1[v][a]\" -map \"[v]\" -map \"[a]\" -c:v libx264 -crf 8 -preset veryfast " + output;

            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();

            System.out.println("Alternating video generation complete.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Alternating video generation failed.");
        }
    }

    String s = "{\"courseActionId\":1,\"courseActionVideoId\":1,\"type\":\"num\",\"value\":30,\"frontVideoUrl\":\"https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/dev/summer/x.mp4\",\"sideVideoUrl\":\"https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/dev/summer/y.mp4\"}";
}
