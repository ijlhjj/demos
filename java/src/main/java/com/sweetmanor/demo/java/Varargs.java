package com.sweetmanor.demo.java;

/**
 * 个数可变形参示例
 *
 * @author ijlhjj
 * @version 1.0 2014-10-24
 */
public class Varargs {

    /**
     * 个数可变形参只能位于参数列表的最后
     */
    public static void print(String... strings) {
        for (String s : strings)
            System.out.println(s);
    }

    public static void main(String[] args) {
        print("疯狂Java讲义", "疯狂Ajax讲义");// 可使用任意个数的参数
    }

}
