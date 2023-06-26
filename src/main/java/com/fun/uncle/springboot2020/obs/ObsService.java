package com.fun.uncle.springboot2020.obs;

import com.fun.uncle.springboot2020.dto.ObsDTO;
import com.huaweicloud.sdk.iam.v3.model.Credential;

import java.util.List;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/5 19:52
 * @Version: 1.0.0
 */
public interface ObsService {


    /**
     * 创建对象通过临时上传地址
     *
     * @param fileName
     * @param uid
     * @param sourceByAdmin
     * @return
     */
    ObsDTO createObjByTempSignUrl(String fileName, Integer uid, boolean sourceByAdmin);


    /**
     * 得到私有的路径
     *
     * @param filePath
     * @return
     */
    String getPrivateUrl(String filePath);

    /**
     * 得到公共的路径
     *
     * @param filePath
     * @return
     */
    String getPublicUrl(String filePath);


    /**
     * 得到最终可以使用的链接
     *
     * @param url
     * @return
     */
    String getFinalUrl(String url);

    /**
     * 得到最终可以使用的链接
     *
     * @param urls
     * @return
     */
    List<String> findFinalUrl(List<String> urls);

    /**
     * 获取临时的ak/sk
     *
     * @return
     */
    Credential getSts();

}
