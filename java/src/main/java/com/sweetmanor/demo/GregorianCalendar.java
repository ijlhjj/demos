package com.sweetmanor.demo;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

/**
 * 格林威治日历 <br />
 * 显示阳历日历的实用算法，其中日期的增减值得借鉴
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class GregorianCalendar {

    public static void main(String[] args) {
        jdk18();
    }

    public static void jdk18() {
        LocalDate date = LocalDate.now();// JDK1.8新增日历类
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");// 标题，jdk18从Mon开始，beforeJdk18从Sun开始

        date = date.minusDays(today - 1);// 当月1日
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();// 当月1日的星期

        for (int i = 1; i < value; i++)// 第一行缩进
            System.out.print("    ");

        while (date.getMonthValue() == month) {// 循环当月
            System.out.printf("%3d", date.getDayOfMonth());

            // 当日标记
            if (date.getDayOfMonth() == today)
                System.out.print("*");
            else
                System.out.print(" ");

            date = date.plusDays(1);

            if (date.getDayOfWeek().getValue() == 1)// 周一换行
                System.out.println();
        }
    }

    public static void beforeJdk18() {
        Locale.setDefault(Locale.US);// 示例使用美国日期格式

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(MONTH);
        int today = calendar.get(DAY_OF_MONTH);

        calendar.set(DAY_OF_MONTH, 1);// 当月1日
        int weekday = calendar.get(DAY_OF_WEEK);// 当月1日的星期

        int firstDayOfWeek = calendar.getFirstDayOfWeek();// 星期的起始日，中国和美国都是星期天
        int indent = weekday - firstDayOfWeek;// 当月1日的缩进

        // 输出星期的短名称
        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();// 星期的短格式名称
        for (String name : weekdayNames) {
            if (name.length() > 1)
                System.out.printf("%4s", name);
        }
        System.out.println();

        for (int i = 1; i <= indent; i++) // 第一行缩进
            System.out.print("    ");

        while (calendar.get(MONTH) == month) {// 循环当月
            // 输出日期
            int day = calendar.get(DAY_OF_MONTH);
            System.out.printf("%3d", day);

            // 当日加一个标记*
            if (day == today)
                System.out.print("*");
            else
                System.out.print(" ");

            calendar.add(DAY_OF_MONTH, 1);

            if (calendar.get(DAY_OF_WEEK) == firstDayOfWeek) // 星期第一天换行
                System.out.println();
        }
    }

}
