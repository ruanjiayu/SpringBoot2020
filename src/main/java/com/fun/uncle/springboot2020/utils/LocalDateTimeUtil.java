package com.fun.uncle.springboot2020.utils;

import com.fun.uncle.springboot2020.constant.enums.DateTimeFormatterEnum;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @Description: 时间工具类
 * @Author: Summer
 * @DateTime: 2020/10/13 10:39 上午
 * @Version: 0.0.1-SNAPSHOT
 */
public class LocalDateTimeUtil {

    private static final ZoneId zone = ZoneId.systemDefault();

    public static final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);

    /**
     * 默认的时间转换
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(String date) {
        return toLocalDateTime(date, DateTimeFormatterEnum.FORMAT_YMD_HMS.getType());
    }

    /**
     * 将String转化为LocalDateTime
     *
     * @param date
     * @param type {@link DateTimeFormatterEnum}
     * @return
     */
    public static LocalDateTime toLocalDateTime(String date, Integer type) {
        try {
            if (StringUtils.isNotBlank(date)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                //只有年月的话,需要使用LocalDate去解析
                switch (format) {
                    case FORMAT_YM:
                        date += "01";
                        break;
                    case FORMAT_YMD:
                        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatterEnum.FORMAT_YMD.getFormatter()), LocalTime.MIN);
                    case FORMAT_Y_M:
                        date += "-01";
                        break;
                    case FORMAT_Y_M_D:
                        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatterEnum.FORMAT_Y_M_D.getFormatter()), LocalTime.MIN);
                }
                return LocalDateTime.parse(date, format.getFormatter());
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为字符串格式
     *
     * @param dateTime
     * @param type     {@link DateTimeFormatterEnum}
     * @return
     */
    public static String format(LocalDateTime dateTime, Integer type) {
        try {
            if (Objects.nonNull(dateTime)) {
                DateTimeFormatterEnum format = DateTimeFormatterEnum.getByType(type);
                return format.getFormatter().format(dateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为字符串格式
     *
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatterEnum format) {
        try {
            if (Objects.nonNull(dateTime)) {
                return format.getFormatter().format(dateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为字符串格式
     *
     * @param dateTime
     * @param format DateTimeFormatter.ofPattern("yyyy-MM-dd")
     * @return
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatter format) {
        try {
            if (Objects.nonNull(dateTime)) {
                return format.format(dateTime);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将Date转化为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (Objects.nonNull(date)) {
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            return localDateTime;
        }
        return null;
    }

    /**
     * 将LocalDateTime转化为Date
     *
     * @param dateTime
     * @return
     */
    public static Date toDate(LocalDateTime dateTime) {
        if (Objects.nonNull(dateTime)) {
            Instant instant = dateTime.atZone(zone).toInstant();
            return Date.from(instant);
        }
        return null;
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(zone).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(zone).toInstant().getEpochSecond();
    }

    /**
     * 获取该月月初时间
     *
     * @return
     */
    public static LocalDateTime firstDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        return LocalDateTime.of(localDate.with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
    }

    /**
     * 获取输入该天的的起始
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atStartOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atStartOfDay();
        }
        return null;
    }

    /**
     * 获取输入该天的最后时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atEndOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.plusDays(1).atStartOfDay().minusSeconds(1);
        }
        return null;
    }

    /**
     * 获取输入该天的的起始
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime atStartOfDay(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.toLocalDate().atStartOfDay();
        }
        return null;
    }

    /**
     * 获取输入该天最后的时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime atEndOfDay(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.toLocalDate().plusDays(1).atStartOfDay().minusSeconds(1);
        }
        return null;
    }

    /**
     * 判断当前时间是否在指定时间段内
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Boolean betweenAnd(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        return betweenAnd(now, startTime, endTime);
    }

    /**
     * 判断指定时间是否在对应时间段内
     *
     * @param time      指定时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Boolean betweenAnd(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
        if (Objects.isNull(startTime) && Objects.isNull(endTime)) {
            return true;
        }

        if (Objects.isNull(startTime) && Objects.nonNull(endTime)) {
            return time.isBefore(endTime);
        }

        if (Objects.nonNull(startTime) && Objects.isNull(endTime)) {
            return time.isAfter(startTime);
        }
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    /**
     * 计算【充值】后结束时间
     * 如果开始时间为00:00:00, 则包含当天
     * 举例：delta=2
     * startTime=5.22 00:00:00,  则endTime=5.23 23:59:58
     * startTime=5.22 00:00:01,  则endTime=5.24 23:59:58
     *
     * @param startTime
     * @param delta
     * @return
     */
    public static LocalDateTime getEndTimeWithCharge(LocalDateTime startTime, Integer delta) {
        if (Objects.isNull(startTime) || Objects.isNull(delta)) {
            return null;
        }
        // 当是开始时间为00:00:00, 则包含当天
        if (equals(LocalTime.MIN, startTime.toLocalTime())) {
            delta--;
        }
        return LocalDateTime.of(startTime.toLocalDate().plusDays(delta), MAX_TIME);
    }

    /**
     * LocalTime只比较时分秒
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean equals(LocalTime o1, LocalTime o2) {
        if (o1 == o2) {
            return true;
        }

        if (null == o1 || null == o2) {
            return false;
        }

        return o1.getHour() == o2.getHour() && o1.getMinute() == o2.getMinute() && o1.getSecond() == o2.getSecond();
    }

    /**
     * 从现在 到 结束时间 之间的时间间隔（秒）
     *
     * @param activityEndTime 活动结束时间
     * @param delayedHours    需要延长的时间（小时）
     * @return
     */
    public static Long getTimeline(LocalDateTime activityEndTime, int delayedHours) {
        Duration duration = Duration.between(LocalDateTime.now(), activityEndTime);
        return duration.toMillis() / 1000 + delayedHours * 60 * 60;
    }

    /**
     * 从现在 到 结束时间 之间的时间间隔（秒）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Long getTimeline(LocalDateTime startTime, LocalDateTime endTime) {
        return getTimeline(startTime, endTime, 0);
    }

    /**
     * 从现在 到 结束时间 之间的时间间隔 + 加上指定延长的时间（秒）
     *
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param delayedHours 延长时间
     * @return
     */
    public static Long getTimeline(LocalDateTime startTime, LocalDateTime endTime, int delayedHours) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMillis() / 1000 + delayedHours * 60 * 60;
    }

    /**
     * 获取当前时间到明天之间的时间间隔
     *
     * @return
     */
    public static Long getTimeline() {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, now.plusDays(1).toLocalDate().atStartOfDay()).toMillis();
    }

    /**
     * 得到一半的时间
     *
     * @return
     */
    public static LocalDateTime getHalfTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime halfTime = LocalDateTime.of(now.toLocalDate(), LocalTime.of(now.getHour(), 30));
        LocalDateTime startTime = LocalDateTime.of(now.toLocalDate(), LocalTime.of(now.getHour(), 0));
        return now.isAfter(halfTime) ? halfTime : startTime;
    }

    /**
     * 整个月份消费。
     * 每天消费
     *
     * @param optMonth
     * @param consumer
     */
    public static void consumerMonth(String optMonth, Consumer<LocalDate> consumer) {
        String startDayStr = optMonth + "-01";
        LocalDate startDay = LocalDate.parse(startDayStr);
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 32; i++) {
            LocalDate optDate = startDay.plusDays(i);
            // 跳出循环
            // 1. 选中的日期大于等于今天
            // 2. 选中的日期已经翻月了
            if (!optDate.isBefore(now) || optDate.isAfter(startDay.with(TemporalAdjusters.lastDayOfMonth()))) {
                break;
            }
            consumer.accept(optDate);
        }
    }

    /**
     * 整个月份消费。
     * 每半小时归档一次
     *
     * @param optMonth
     * @param consumer
     */
    public static void consumerDay(String optMonth, Consumer<LocalDateTime> consumer) {
        String startDayStr = optMonth + "-01";
        LocalDate startDay = LocalDate.parse(startDayStr);
        LocalDate nowDate = LocalDate.now();
        LocalDateTime nowTime = LocalDateTime.now();
        for (int i = 0; i < 32; i++) {
            LocalDate optDate = startDay.plusDays(i);
            // 跳出循环
            // 1. 选中的日期大于今天
            // 2. 选中的日期已经翻月了
            if (optDate.isAfter(nowDate) || optDate.isAfter(startDay.with(TemporalAdjusters.lastDayOfMonth()))) {
                break;
            }
            // 时间波动
            for (int t = 0; t < 48; t++) {
                LocalDateTime optTime = LocalDateTime.of(optDate, LocalTime.MIN.plusMinutes(t * 30));
                if (optTime.isAfter(nowTime)) {
                    break;
                }
                consumer.accept(optTime);
            }
        }
    }

}
