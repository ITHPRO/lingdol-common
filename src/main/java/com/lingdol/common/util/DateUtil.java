package com.lingdol.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转成日期格式
     */
    public static Date convert(String dateStr, String format) {
        try {
            if (StringUtil.existEmpty(dateStr, format)) return null;

            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转成日期格式
     */
    public static Date convertToDate(String dateStr) {
        return convert(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * 字符串转成时间格式
     */
    public static Date convertToTime(String dateStr) {
        return convert(dateStr, DEFAULT_TIME_FORMAT);
    }

    /**
     * 日期格式转成字符串
     */
    public static String format(Date date, String format) {
        if (ObjectUtil.existEmpty(date, format)) return "";

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期格式转成字符串
     * 格式：yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 日期格式转成字符串
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, DEFAULT_TIME_FORMAT);
    }

    /**
     * 往前或往后变更天数（负数就是往前，正数是往后）
     */
    public static Date addDate(Date date, int day) {
        if (null == date) {
            return null;
        }
        Calendar calendar = newCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 往前或往后变更月数（负数就是往前，正数是往后）
     */
    public static Date addMonth(Date date, int month) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 往前或往后变更分钟数（负数就是往前，正数是往后）
     */
    public static Date addMinute(Date date, int minute) {
        if (null == date) {
            return null;
        }
        Calendar calendar = newCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 往前或往后变更秒数（负数就是往前，正数是往后）
     */
    public static Date addSecond(Date date, int second) {
        if (null == date) {
            return null;
        }
        Calendar calendar = newCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 将日期转化成当天的开始时间（该日期最小时间）
     */
    public static Date convertToStartTime(Date time) {
        Calendar calendar = newCalendar();
        calendar.setTime(time);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 将日期转化成当天的结束时间（该日期最大时间）
     * 注意：不含毫秒
     */
    public static Date convertToEndTime(Date time) {
        Calendar calendar = newCalendar();
        calendar.setTime(time);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 获取指定月份的第一天的最开始
     */
    public static Date getMonthBeginDate(Date time) {
        Calendar calendar = newCalendar();
        calendar.setTime(time);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return convertToStartTime(calendar.getTime());
    }

    /**
     * 获取指定月份的最后一天最结束
     */
    public static Date getMonthEndDate(Date time) {
        Calendar calendar = newCalendar();
        calendar.setTime(time);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return convertToEndTime(calendar.getTime());
    }

    private static Calendar newCalendar() {
        return Calendar.getInstance(TimeZone.getDefault());
    }

    /**
     * 计算两个日期之间相差的天数
     */
    public static long diffDays(Date startDate, Date endDate) {
        Calendar start = newCalendar();
        start.setTime(startDate);
        // 清除时间部分，只比较日期
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = newCalendar();
        end.setTime(endDate);
        // 清除时间部分，只比较日期
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        long startTimeInMillis = start.getTimeInMillis();
        long endTimeInMillis = end.getTimeInMillis();

        return (endTimeInMillis - startTimeInMillis) / (24 * 60 * 60 * 1000);
    }

    /**
     * 判断某个日期是否为今天
     */
    public static boolean isToday(Date date) {
        if (date == null) return false;

        return isSameDay(date, new Date());
    }

    /**
     * 两个日期是否是同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * LocalDateTime转成Date类型
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDate转成Date类型
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay().atZone(zoneId).toInstant());
    }

}
