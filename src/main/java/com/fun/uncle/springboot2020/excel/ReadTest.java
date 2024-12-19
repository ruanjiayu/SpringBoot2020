package com.fun.uncle.springboot2020.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: summer
 * @CreateDate: 2024/11/27 10:43
 * @Version: 1.0.0
 */
public class ReadTest {


    private static String STR_FORMAT = "接收：%s" +
            "发送：%s";

    public static void main(String[] args) {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "xxx.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        Map<LocalDateTime, SimplePadData> dataMap = new HashMap<>();

        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("recordTime", (JSONObject.parseObject(demoData.getString()).getString("recordTime")));
//                jsonObject.put("rxMB", (JSONObject.parseObject(demoData.getString()).getLongValue("rxBytes")));
//                jsonObject.put("txKB", (JSONObject.parseObject(demoData.getString()).getLongValue("txBytes")));

                SimplePadData data = new SimplePadData();
                LocalDateTime recordTime = LocalDateTime.parse(JSONObject.parseObject(demoData.getString()).getString("recordTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                data.setRecordTime(recordTime);
                data.setRx(JSONObject.parseObject(demoData.getString()).getLongValue("rxBytes"));
                data.setTx(JSONObject.parseObject(demoData.getString()).getLongValue("txBytes"));
                dataMap.put(recordTime, data);
            }
        })).sheet().doRead();

//        System.out.println("==============");
//        System.out.println(results);

        List<String> results = new ArrayList<>();
        long total = 0;
        // 获取想要的值
        for (LocalDateTime[] slot : TimeData.convertedTimeSlots()) {
            // 打印转换后的时间范围
            SimplePadData start = dataMap.get(slot[0]);
            SimplePadData end = dataMap.get(slot[1]);

            long consumerRx = end.getRx() - start.getRx();
            long consumerTX = end.getTx() - start.getTx();
            total = total + consumerRx + consumerTX;

            String vo = String.format(STR_FORMAT, convertSize(consumerRx), convertSize(consumerTX));
            System.out.println(vo);
            System.out.println(convertSize(consumerRx + consumerTX));
            results.add(vo);
        }

//        System.out.println(results);
//        System.out.println(convertSize(total));
    }


    public static String convertSize(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            // 如果小于1KB，直接以字节为单位
            return sizeInBytes + "B";
        } else {
            // 计算KB、MB、GB
            double sizeInKB = sizeInBytes / 1024.0;
            if (sizeInKB < 1024) {
                return String.format("%.2fMB", sizeInKB / 1024.0);
            } else {
                double sizeInMB = sizeInKB / 1024.0;
                if (sizeInMB < 1024) {
                    return String.format("%.2fMB", sizeInMB);
                } else {
                    double sizeInGB = sizeInMB / 1024.0;
                    return String.format("%.2fGB", sizeInGB);
                }
            }
        }
    }
}
