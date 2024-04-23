package com.fun.uncle.springboot2020.es;

import com.fun.uncle.springboot2020.ApplicationTests;
import com.fun.uncle.springboot2020.es.handler.EsBaseOptHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/4/23 14:09
 * @Version: 1.0.0
 */
public class EsBaseOptHandlerTests extends ApplicationTests {

    @Autowired
    private EsBaseOptHandler esBaseOptHandler;

    @Test
    public void createIndex() {
        esBaseOptHandler.createIndex("index1");
    }
}
