package com.fun.uncle.springboot2020.dto;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/6 16:07
 * @Version: 1.0.0
 */
public class ObsDTO {

    /**
     * 临时上传路径
     */
    private String tempSignUrl;

    /**
     * 图片地址
     */
    private String filePath;

    public String getTempSignUrl() {
        return tempSignUrl;
    }

    public void setTempSignUrl(String tempSignUrl) {
        this.tempSignUrl = tempSignUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
