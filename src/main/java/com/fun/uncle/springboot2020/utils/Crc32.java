package com.fun.uncle.springboot2020.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/2/20 19:27
 * @Version: 1.0.0
 */
public class Crc32 {


    /**
     * 返回远程文件crc32值
     *
     * @param url 远端文件Url
     * @return crc32
     */
    public static String urlFileCRC32(String url) {
        try {
            //对本地文件命名
            String fileName = "ota" + System.currentTimeMillis();
            // 创建一个临时路径
            File file = File.createTempFile("file", fileName);
            //下载
            URL urlfile = new URL(url);

            try (InputStream inStream = urlfile.openStream();
                 OutputStream os = new FileOutputStream(file)) {
                int bytesRead;
                byte[] buffer = new byte[8192];
                while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                return fileStreamCRC(new FileInputStream(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 文件流处理
     *
     * @param inputStream
     * @return
     */
    public static String fileStreamCRC(FileInputStream inputStream) {
        CRC32 crc32 = new CRC32();
        String crcStr = null;
        try (CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, crc32)) {
            while (checkedInputStream.read() != -1) {
            }
            crcStr = Long.toHexString(crc32.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crcStr.toUpperCase();
    }

    public static void main(String[] args) {

        String path2 = "https://publicduoguan.oss-cn-hangzhou.aliyuncs.com/ota/s5/S5_v1.0.55.ota.bin";
        System.out.println(urlFileCRC32(path2).toUpperCase());
    }
}
