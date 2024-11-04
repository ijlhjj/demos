package com.sweetmanor.demo.java;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class DateFormatDemo {

    public static void main(String[] args) {
        Date date = new Date();
        DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA); // 中国日期
        System.out.println("中国日期：\t" + formater.format(date));

        formater = DateFormat.getDateInstance(DateFormat.FULL, Locale.US); // 加拿大日期
        System.out.println("美国日期：\t" + formater.format(date));

        formater = DateFormat.getDateInstance(DateFormat.FULL, Locale.JAPAN); // 日本日期
        System.out.println("日本日期：\t" + formater.format(date));

        formater = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN); // 德国日期
        System.out.println("德国日期：\t" + formater.format(date));
        System.out.println();

        String b = String.format(Locale.US, "%tB", date);
        System.out.println("月份的英文全写: " + b);

        String d = String.format("%tA", date);
        System.out.println("星期: " + d);

        String e = String.format("%tY", date);
        System.out.println("4位年份: " + e);

        String g = String.format("%tm", date);
        System.out.println("2位月份: " + g);

        String h = String.format("%td", date);
        System.out.println("2位日期: " + h);
    }

}
