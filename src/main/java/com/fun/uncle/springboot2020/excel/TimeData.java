package com.fun.uncle.springboot2020.excel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/11/27 10:45
 * @Version: 1.0.0
 */
public class TimeData {

    public static String[][] timeSlots = {
            {"11:33", "12:19"}
    };

    public static void main(String[] args) {
        for (LocalDateTime[] slot : convertedTimeSlots()) {
            // 打印转换后的时间范围
            System.out.println(slot[0] + " to " + slot[1]);
        }
    }


    public static List<LocalDateTime[]> convertedTimeSlots() {


        List<LocalDateTime[]> convertedTimeSlots = new ArrayList<>();
        String yearMonthDay = "2024-11-21"; // 定义年月日

        for (String[] slot : timeSlots) {
            LocalDateTime startTime = LocalDateTime.parse(yearMonthDay + " " + slot[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime endTime;

            if (slot.length > 1 && !slot[1].isEmpty()) {
                endTime = LocalDateTime.parse(yearMonthDay + " " + slot[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } else {
                endTime = startTime; // 如果第二个元素不存在，则使用第一个元素
            }

            // 设置开始时间为00秒，结束时间为59秒
            startTime = startTime.withSecond(0).withNano(0);
            endTime = endTime.withSecond(58).withNano(0);

            // 将调整后的时间转换回字符串并添加到列表中
//            convertedTimeSlots.add(new String[]{
//                    startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            });

            convertedTimeSlots.add(new LocalDateTime[]{
                    startTime,
                    endTime
            });
        }

        return convertedTimeSlots;
    }
}

