package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.FileInputStream;

/**
 * 文件输入流示例
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class FileInputStreamDemo1 {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml");)// 创建字节输入流
        {
            byte[] bbuf = new byte[1024]; // 创建缓存字节数组
            int hasRead = 0; // 实际读取的字节数
            while ((hasRead = fis.read(bbuf)) > 0) { // 循环读入
                System.out.print(new String(bbuf, 0, hasRead));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
