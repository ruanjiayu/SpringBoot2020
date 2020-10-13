package com.fun.uncle.springboot2020.config.redis;

import com.fun.uncle.springboot2020.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis的基础操作
 * @Author: fan
 * @DateTime: 2020/7/26 2:15 下午
 * @Version: 0.0.1-SNAPSHOT
 */

@Component
@Slf4j
public class RedisBasicOperation implements RedisBasicKeyOperation, RedisBasicStringOperation, RedisBasicHashOperation, RedisBasicListOperation, RedisBasicSetOperation, RedisBasicZSetOperation {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /*key 的相关操作*/
    @Override
    public boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }


    @Override
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                return stringRedisTemplate.expire(key, time, timeUnit);
            }
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return false;
    }


    @Override
    public Long getExpire(String key) {
        try {
            return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }


    @Override
    public boolean hasKey(String key) {
        try {
            if (StringUtils.isNotBlank(key)) {
                return stringRedisTemplate.hasKey(key);
            }
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return false;
    }


    @Override
    public void del(String key) {
        try {
            stringRedisTemplate.delete(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    @Override
    public void del(String... keys) {
        try {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    stringRedisTemplate.delete(keys[0]);
                } else {
                    stringRedisTemplate.delete(CollectionUtils.arrayToList(keys));
                }
            }
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    /*String 类型*/
    @Override
    public String getString(String key) {
        try {
            return key == null ? null : stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }

    @Override
    public boolean setString(String key, String value, Long time, TimeUnit timeUnit) {
        if (StringUtils.isAnyBlank(key, value)) {
            return false;
        }
        try {
            if (time > 0) {
                if (TimeUnit.SECONDS == timeUnit) {
                    // 解决服务雪崩情况
                    time += RandomUtil.getRandom(60);
                }
                stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                stringRedisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return false;
        }
    }


    @Override
    public Long increment(String key, long delta) {
        try {
            return stringRedisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }


    @Override
    public Long increment(String key, long delta, long time) {
        return this.increment(key, delta, time, TimeUnit.SECONDS);
    }

    @Override
    public Long increment(String key, long delta, long time, TimeUnit timeUnit) {
        try {
            long num = stringRedisTemplate.opsForValue().increment(key, delta);
            // 兼容异常情况
            if (getExpire(key) < 0) {
                expire(key, time, timeUnit);
            }
            return num;
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    /*hash 类型操作*/
    @Override
    public void putHash(String key, String hashKey, String hashValue) {
        if (StringUtils.isAnyBlank(key, hashKey, hashValue)) {
            return;
        }
        try {
            stringRedisTemplate.opsForHash().put(key, hashKey, hashValue);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    @Override
    public String getHash(String key, String hashKey) {
        if (StringUtils.isAnyBlank(key, hashKey)) {
            return null;
        }
        try {
            return (String) stringRedisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }


    /*List 相关操作*/
    @Override
    public void rightPush(String key, String value) {
        if (StringUtils.isAnyBlank(key, value)) {
            return;
        }
        try {
            stringRedisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }

    @Override
    public void rightPushAll(String key, List<String> values) {
        if (Objects.isNull(key) || CollectionUtils.isEmpty(values)) {
            return;
        }
        try {
            stringRedisTemplate.opsForList().rightPushAll(key, values);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    @Override
    public void leftPushAll(String key, List<String> values) {
        if (Objects.isNull(key) || CollectionUtils.isEmpty(values)) {
            return;
        }
        try {
            stringRedisTemplate.opsForList().leftPushAll(key, values);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    @Override
    public String leftPop(String key) {
        if (Objects.isNull(key)) {
            return null;
        }
        try {
            return stringRedisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }


    @Override
    public String index(String key, long index) {
        if (Objects.isNull(key)) {
            return null;
        }
        try {
            return stringRedisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }


    @Override
    public String rightPop(String key) {
        if (Objects.isNull(key)) {
            return null;
        }
        try {
            return stringRedisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }

    @Override
    public Long listSize(String key) {
        if (Objects.isNull(key)) {
            return 0L;
        }
        try {
            return stringRedisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return 0L;
        }
    }


    @Override
    public List<String> range(String key, Integer start, Integer end) {
        if (Objects.isNull(key)) {
            return null;
        }
        try {
            return stringRedisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("【redis异常】", e);
            return null;
        }
    }


    @Override
    public void putAllHash(String key, Map<String, String> map, Long time, TimeUnit timeUnit) {
        if (Objects.isNull(key) || CollectionUtils.isEmpty(map)) {
            return;
        }
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            stringRedisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }

    /*set 相关操作*/

    @Override
    public void addSet(String key, String value) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return;
        }
        try {
            stringRedisTemplate.boundSetOps(key).add(value);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
    }


    @Override
    public boolean setContains(String key, String value) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return false;
        }
        try {
            return stringRedisTemplate.boundSetOps(key).isMember(value);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return false;
    }

    @Override
    public Set<String> getSet(String key) {
        if (Objects.isNull(key)) {
            return Collections.emptySet();
        }
        try {
            return stringRedisTemplate.boundSetOps(key).members();
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return Collections.emptySet();
    }


    @Override
    public Long getSetSize(String key) {
        if (Objects.isNull(key)) {
            return 0L;
        }
        try {
            return stringRedisTemplate.boundSetOps(key).size();
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return 0L;
    }

    /*ZSet相关操作*/

    @Override
    public Double incrementScoreZSet(String key, String value) {
        return incrementScoreZSet(key, value, 1);
    }

    @Override
    public Double incrementScoreZSet(String key, String value, double delta) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return null;
        }
        try {
            return stringRedisTemplate.boundZSetOps(key).incrementScore(value, delta);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    @Override
    public Double getScoreZSet(String key, String value) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return null;
        }
        try {
            return stringRedisTemplate.boundZSetOps(key).score(value);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    @Override
    public Boolean setScoreZSet(String key, String value, Double score) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return null;
        }
        try {
            return stringRedisTemplate.boundZSetOps(key).add(value, score);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    @Override
    public Long getRankZSet(String key, String value, boolean desc) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            return null;
        }
        try {
            if (desc) {
                return stringRedisTemplate.boundZSetOps(key).reverseRank(value);
            } else {
                return stringRedisTemplate.boundZSetOps(key).rank(value);
            }
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> getReverseRangeWithScoresZSet(String key, Long start, Long end) {
        if (Objects.isNull(key) || Objects.isNull(start) || Objects.isNull(end)) {
            return null;
        }
        try {
            return stringRedisTemplate.boundZSetOps(key).reverseRangeWithScores(start, end);
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return null;
    }

    @Override
    public Double getReverseRangeWithScoresZSet(String key, Long index) {
        Double zero = 0d;
        Set<ZSetOperations.TypedTuple<String>> tuples = getReverseRangeWithScoresZSet(key, index - 1, index - 1);
        if (CollectionUtils.isEmpty(tuples)) {
            return zero;
        } else {
            for (ZSetOperations.TypedTuple<String> tuple : tuples) {
                Double score = tuple.getScore();
                return Objects.isNull(score) ? zero : score;
            }
        }
        return zero;
    }

    @Override
    public Long getZSetSize(String key) {
        if (Objects.isNull(key)) {
            return 0L;
        }
        try {
            return stringRedisTemplate.boundZSetOps(key).size();
        } catch (Exception e) {
            log.error("【redis异常】", e);
        }
        return 0L;
    }

}
