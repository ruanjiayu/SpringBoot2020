package com.fun.uncle.springboot2020.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description:
 * @Author: Summer
 * @DateTime: 2020/7/20 11:46 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class HelloControllerTest {

    @BeforeEach
    void setUp() {
        System.out.println("===before method===");
    }

    @AfterEach
    void tearDown() {
        System.out.println("===after method===");
    }

    @Test
    void hello() {
        System.out.println("ruanjiayu");
    }
}