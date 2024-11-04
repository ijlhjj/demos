package com.sweetmanor.demo.java;

/**
 * 特殊的浮点数值
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class SpecialFloatValue {

    public static void main(String[] args) {
        float f1 = 5.2345556f;// float类型有效位是7位
        System.out.println(f1);

        System.out.println(Float.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY);// 正负无穷大是相等的
        System.out.println(Float.NaN == Float.NaN);// 非数是不等的

        System.out.println(6.5 / 0);// 正数除0得正无穷
        System.out.println(-6.5 / 0);// 负数除0得负无穷

        // System.out.println(6 / 0);// 整数除0将报错
    }

}
