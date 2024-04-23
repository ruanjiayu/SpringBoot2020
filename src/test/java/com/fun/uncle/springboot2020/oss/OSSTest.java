package com.fun.uncle.springboot2020.oss;

import com.alibaba.fastjson.JSON;
import com.fun.uncle.springboot2020.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2023/6/26 17:32
 * @Version: 1.0.0
 */
public class OSSTest extends ApplicationTests {

    @Autowired
    private OssService ossService;

    @Test
    public void stringTest() {
        System.out.println(JSON.toJSONString(ossService.getStsToken()));
    }
}
