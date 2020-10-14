package com.fun.uncle.springboot2020.utils;

import com.fun.uncle.springboot2020.config.redis.RedisCacheManager;
import com.fun.uncle.springboot2020.dto.base.RandomConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 幸运抽奖
 * @Author: Summer
 * @DateTime: 2020/10/13 10:14 上午
 * @Version: 0.0.1-SNAPSHOT
 */
@Component
public class LuckyDrawUtil {

    @Autowired
    private RedisCacheManager redisCacheManager;


    /**
     * 返回抽奖的结果(奖金池算法)
     *
     * @param randomConfigDTOList 随机配置项
     * @param defaultConfig       默认配置项
     * @param activityPrefix      活动前缀
     * @param randomNum           随机范围值
     * @return
     */
    public <T extends RandomConfigDTO> T luckyDraw(List<T> randomConfigDTOList, T defaultConfig, String activityPrefix, int randomNum) {
        LocalDate today = LocalDate.now();
        Map<String, T> randomConfigMap = buildRandomConfigMap(randomConfigDTOList);
        // 初始化相应的配置，尽量减少对应的异常
        T result = defaultConfig;

        for (Map.Entry<String, T> entry : randomConfigMap.entrySet()) {
            if (inRange(entry.getKey(), randomNum)) {
                T config = entry.getValue();
                String grantCountKey = String.format(activityPrefix, config.getId(), today);
                Long grantCount = redisCacheManager.increment(grantCountKey, 1L, LocalDateTimeUtil.getTimeline(), TimeUnit.MILLISECONDS);
                // 获取到无限额度的量
                if (config.getStock() == -1) {
                    result = config;
                }
                // 获取到有额度的量
                else if (grantCount <= config.getStock()) {
                    result = config;
                }
                // 获取到默认的配置
                else {
                    result = defaultConfig;
                }
            }
        }
        return result;
    }


    /**
     * 返回抽奖的结果(幸运大转盘算法)
     *
     * @param randomConfigDTOList 随机配置项
     * @param defaultConfig       默认配置项
     * @param activityPrefix      活动前缀
     * @return
     */
    public <T extends RandomConfigDTO> T luckyDraw(List<T> randomConfigDTOList, T defaultConfig, String activityPrefix) {
        LocalDate today = LocalDate.now();
        Map<String, T> randomConfigMap = buildRandomConfigMap(randomConfigDTOList);
        Integer sumWeight = randomConfigDTOList.stream().map(RandomConfigDTO::getWeight).reduce(0, Integer::sum);
        int randomNum = RandomUtil.getRandom(sumWeight) + 1;
        // 初始化相应的配置，尽量减少对应的异常
        T result = defaultConfig;

        for (Map.Entry<String, T> entry : randomConfigMap.entrySet()) {
            if (inRange(entry.getKey(), randomNum)) {
                T config = entry.getValue();
                String grantCountKey = String.format(activityPrefix, config.getId(), today);
                Long grantCount = redisCacheManager.increment(grantCountKey, 1L, LocalDateTimeUtil.getTimeline(), TimeUnit.MILLISECONDS);
                // 获取到无限额度的量
                if (config.getStock() == -1) {
                    result = config;
                }
                // 获取到有额度的量
                else if (grantCount <= config.getStock()) {
                    result = config;
                }
                // 获取到默认的配置
                else {
                    result = defaultConfig;
                }
            }
        }
        return result;
    }

    /**
     * 得到对应的随机值
     *
     * @param redisKey
     * @return
     */
    public String getRandomNum(String redisKey) {
        String value = redisCacheManager.getSPop(redisKey);
        return value;
    }

    /**
     * 构建对应的随机map
     *
     * @param configList 随机配置奖励
     * @return
     */
    private <T extends RandomConfigDTO> Map<String, T> buildRandomConfigMap(List<T> configList) {
        Map<String, T> configMap = new HashMap<>();
        int preSum = 0;
        int curSum;
        for (T config : configList) {
            curSum = config.getWeight() + preSum;
            int minRange = preSum + 1;
            int maxRange = curSum;
            configMap.put(minRange + "_" + maxRange, config);
            preSum = curSum;
        }
        return configMap;
    }

    /**
     * 判断数据是否在内
     *
     * @param range     范围
     * @param randomNum 随机值
     * @return
     */
    private boolean inRange(String range, int randomNum) {
        String[] weightRanges = range.split("_");
        if (weightRanges.length != 2) {
            return false;
        }
        int minRange = Integer.parseInt(weightRanges[0]);
        int maxRange = Integer.parseInt(weightRanges[1]);
        return randomNum >= minRange && randomNum <= maxRange;
    }

}
