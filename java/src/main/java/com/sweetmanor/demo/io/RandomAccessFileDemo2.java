package com.sweetmanor.demo.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机读取示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class RandomAccessFileDemo2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(File.createTempFile("out-", ".log"), "rw");// 以读写方式打开文件
        raf.seek(raf.length());// 移动文件指针到文件末尾
        raf.write("追加的内容\r\n".getBytes());// 输出追加内容，\r\n为控制换行
    }

}
