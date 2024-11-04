package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机读取示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class RandomAccessFileDemo1 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(Const.classPath + "log4j2.xml", "r");// 以只读方式打开文件
        System.out.println("文件指针的初始位置：" + raf.getFilePointer());// 获取初始文件指针位置，初始位置为0
        raf.seek(30);// 移动文件指针位置
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        while ((hasRead = raf.read(buffer)) > 0) {
            System.out.print(new String(buffer, 0, hasRead));
        }
    }

}
