package com.sweetmanor.demo;

/**
 * Unicode与字符之间的相互转换
 *
 * @author ijlhjj
 * @version 1.0 2016-06-14
 */
public class ConvertCharacter {

    public static void main(String[] args) {
        // 字符转为Unicode
        String text = "温馨佳园";
        char[] charArray = text.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : charArray) {
            builder.append((int) c + " ");
        }
        System.out.println(builder.toString());

        // Unicode转为字符
        long code = 0X6c49L;
        System.out.println(((char) code) + "");
    }

}
