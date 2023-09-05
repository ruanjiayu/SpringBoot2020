package com.fun.uncle.springboot2020.ffmpeg.superior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 单个视频合并成流来处理
 */
public class SingleVideoGeneration {

    public static void main(String[] args) {
        String inputFilePath = "/Users/xian/Desktop/s.mp4";
        String outputDirectory = "/Users/xian/Desktop/";

        int[] durations = {20, 50}; // Desired durations in seconds

        for (int duration : durations) {
            String outputFilePath = outputDirectory + "output_" + duration + "s.mp4";
            generateVideo(inputFilePath, duration, outputFilePath);
        }
    }

    public static void generateVideo(String inputFilePath, int duration, String outputFilePath) {
        try {
            String command = "/usr/local/bin/ffmpeg -y -i " + inputFilePath +
                    " -filter_complex \"";
            int numCopies = duration / 10; // Number of copies needed for the desired duration

            for (int i = 0; i < numCopies; i++) {
                command += "[0:v][0:a]";
            }
            command += "concat=n=" + numCopies + ":v=1:a=1[v][a]\" -map \"[v]\" -map \"[a]\" -t " + duration + " -c:v libx264 -crf 8 -preset veryfast " + outputFilePath;

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

            System.out.println("Video generation for " + duration + " seconds complete.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Video generation for " + duration + " seconds failed.");
        }
    }
}
