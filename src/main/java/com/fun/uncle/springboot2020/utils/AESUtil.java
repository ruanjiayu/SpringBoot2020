package com.fun.uncle.springboot2020.utils;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.fun.uncle.springboot2020.vo.UserVO;
import org.slf4j.Logger;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/2/26 17:06
 * @Version: 1.0.0
 */
public class AESUtil {


    private final static Logger logger = LoggerUtil.COMMON_INFO;

    private final static byte[] key = "B6217B035CD94F78".getBytes();

    public static void main(String[] args) {

        UserVO userVO = new UserVO();
        userVO.setId(10L);
        userVO.setNickname("阮佳裕测试下😄2");

        // 加密
        String encryptBase64 = encryptBase64(userVO);

        // 解密
        decryptStr(encryptBase64);
        decryptStr("ZCHqsnK4fMkzUkqtvi+QUBnzW8H5BGW2p3IHr7FiAAupOOUVaaoy9v7MP+vqE7hbwXFJNVT6d46+bOs1xMj7Qgo0ds3OPT82aBdPR0unO2lJDmiSSzOqUgeM5bDzp5en");
//        decryptStr("ZejOfDFiQtpMR0jHD14GPVCbr8pRI15j6tmyUjmrcFg=");
    }


    /**
     * 解密数据
     *
     * @param str
     */
    public static void decryptStr(String str) {
        String data = SecureUtil.aes(key).decryptStr(str);
        LoggerUtil.info(logger, "解密数据", data);
    }

    /**
     * 加密
     *
     * @param obj
     */
    public static String encryptBase64(Object obj) {
        String data = JSON.toJSONString(obj);
        LoggerUtil.info(logger, "明文数据", data);

        String encryptData = SecureUtil.aes(key).encryptBase64(data);
        LoggerUtil.info(logger, "加密后的数据", encryptData);

        return encryptData;
    }


}
