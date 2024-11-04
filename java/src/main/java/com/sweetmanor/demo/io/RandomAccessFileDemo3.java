package com.sweetmanor.demo.io;

import java.io.*;

/**
 * 随机读取示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class RandomAccessFileDemo3 {

    public static void insert(File file, long pos, String insertContent) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");// 以读写方式打开文件
        File temp = File.createTempFile("tmp", null);// 创建临时文件来保存插入点之后的内容
        FileOutputStream tempOut = new FileOutputStream(temp);// 以临时文件创建输出流
        FileInputStream tempIn = new FileInputStream(temp);// 以临时文件创建输入流
        temp.deleteOnExit();// 程序退出时删除临时文件

        raf.seek(pos);// 移动文件指定到插入点
        byte[] buffer = new byte[64];
        int hasRead = 0;
        // 读取插入点之后的内容先写入临时文件
        while ((hasRead = raf.read(buffer)) > 0) {
            tempOut.write(buffer, 0, hasRead);
        }
        raf.seek(pos);// 移动文件指定到插入点
        raf.write(insertContent.getBytes());// 插入指定的内容
        // 从临时文件读取内容追加到后面
        while ((hasRead = tempIn.read(buffer)) > 0) {
            raf.write(buffer);
        }
    }

    public static void main(String[] args) throws IOException {
        insert(File.createTempFile("out-", ".log"), 10, "插入的内容\r\n");
    }

}
