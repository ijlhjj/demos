package com.sweetmanor.demo.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 输出重定向示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class RedirectOut {

    public static void main(String[] args) {
        PrintStream out = System.out;// System.out就是一个PrintStream对象
        try (FileOutputStream fos = new FileOutputStream(File.createTempFile("out-", ".log")); // 创建一个字节输出流
             PrintStream ps = new PrintStream(fos);// 用PrintStream包装节点输出流
        ) {
            System.setOut(ps);// 将标准输出重定向到ps输出流，重定向后标准输出内容将直接打印到文件中
            System.out.println("标准输出重定向");
            System.out.println(new RedirectOut());// 打印对象
            System.setOut(out);// 恢复标准输出
            System.out.println("程序运行完毕，请查看日志。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
