package com.github.weijj0528.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;

/**
 * The type New date time test 001.
 *
 * @author William.Wei
 */
public class NewDateTimeTest001 {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 时间日期
        final LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        final LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
        final LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);

        // 秒
        final long epochSecond = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(epochSecond);
        // 毫秒
        final Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
        System.out.println(instant);

        // 获取年月日 时分秒
        System.out.println("年：" + localDateTime.getYear());
        System.out.println("月：" + localDateTime.getMonthValue());
        System.out.println("日：" + localDateTime.getDayOfMonth());
        System.out.println("时：" + localDateTime.getHour());
        System.out.println("分：" + localDateTime.getMinute());
        System.out.println("秒：" + localDateTime.getSecond());
        System.out.println("纳秒：" + localDateTime.getNano());
        System.out.println("第几天：" + localDateTime.getDayOfYear());
        System.out.println("第几周：" + localDateTime.getLong(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        System.out.println("星期几：" + localDateTime.getDayOfWeek().getValue());
        System.out.println("第几季度：" + localDateTime.getLong(IsoFields.QUARTER_OF_YEAR));
        System.out.println("当前季度第几天：" + localDateTime.getLong(IsoFields.DAY_OF_QUARTER));

        // 格式化
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final String format = localDateTime.format(formatter);
        System.out.println(format);
        // 解析
        final LocalDateTime parse = LocalDateTime.parse(format, formatter);
        System.out.println(parse);
    }

}
