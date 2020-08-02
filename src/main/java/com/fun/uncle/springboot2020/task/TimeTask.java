package com.fun.uncle.springboot2020.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时任务
 * @Author: Summer
 * @DateTime: 2020/8/2 11:24 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@Component
@Slf4j
public class TimeTask {


    private int[] people = {9000,2000,3000,1000};

    private int count = 0;
//
//    /**
//     * 每次完成任务后
//     * @throws InterruptedException
//     */
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() throws InterruptedException {
//        if (count < 4) {
//            int timeConsuming = people[count];
//            log.info("fixedDelayTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
//            Thread.sleep(timeConsuming);
//            count++;
//        }
//    }
//
//    /**
//     * 是5秒的倍数才开始执行，中间会进行阻塞。
//     * @throws InterruptedException
//     */
//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void cronTask() throws InterruptedException {
//        if (count < 4) {
//            int timeConsuming = people[count];
//            log.info("cronTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
//            Thread.sleep(timeConsuming);
//            count++;
//        }
//    }
//
//    /**
//     *
//     * @throws InterruptedException
//     */
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() throws InterruptedException {
//        if (count < 4) {
//            int timeConsuming = people[count];
//            log.info("fixedRateTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
//            Thread.sleep(timeConsuming);
//            count++;
//        }
//    }
}
