package com.fun.uncle.springboot2020.oss;

import com.aliyuncs.auth.sts.AssumeRoleResponse;

import java.util.Date;
import java.util.List;

public interface OssService {

    /**
     * 获取sts的token
     *
     * @return token结果
     */
    AssumeRoleResponse.Credentials getStsToken();

    /**
     * 获取OSS公共读地址
     *
     * @param objectKey 文件名称
     * @return
     */
    String getOssPublicUrl(String objectKey);

    /**
     * 获取视频播放地址
     *
     * @param fileName
     * @return
     */
    String getOssPrivateUrl(String fileName);

    String getOssPrivateUrl(String fileName, Date expiration);

    /**
     * 上传文件到oss
     *
     * @param objectName 在OSS的路径
     * @param filePath   在本地的路径
     * @return
     */
    boolean uploadOss(String objectName, String filePath);

    /**
     * 删除视频文件
     *
     * @param fileNames
     * @return
     */
    List<String> deleteFiles(List<String> fileNames);
}
