package com.fun.uncle.springboot2020.ffmpeg.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description: 本地视频合并
 * @Author: summer
 * @CreateDate: 2023/9/5 11:44
 * @Version: 1.0.0
 */
public class VideoConcatenation {

    public static void main(String[] args) {
        String input1 = "/Users/xian/Desktop/x.mp4";
        String input2 = "/Users/xian/Desktop/y.mp4";
        String output = "/Users/xian/Desktop/out2.mp4";

        concatenateVideos(input1, input2, output);
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
