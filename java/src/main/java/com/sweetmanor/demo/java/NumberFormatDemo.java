package com.sweetmanor.demo.java;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 货币格式化示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class NumberFormatDemo {

    public static void main(String[] args) {
        double number = 123456.7;
        System.out.println("货币格式：");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA); // 创建格式化对象
        // 输出格式化货币格式
        System.out.println("Locale.CHINA：" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Locale.US：" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        System.out.println("Locale.ENGLISH：" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
        System.out.println("Locale.TAIWAN：" + format.format(number));
    }

}
