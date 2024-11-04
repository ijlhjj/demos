package com.sweetmanor.demo.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * PrintStream示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class PrintStreamDemo1 {

    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream(File.createTempFile("out-", ".log")); // 创建一个字节输出流
             PrintStream ps = new PrintStream(fos);// 用PrintStream包装字节输出流，System.out就是一个PrintStream对象
        ) {
            ps.println("普通字符串");
            ps.println(new PrintStreamDemo1());// 输出对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
