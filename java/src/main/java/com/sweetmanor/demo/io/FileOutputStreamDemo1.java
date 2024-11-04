package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 用输入输出流实现文件的复制
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class FileOutputStreamDemo1 {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml"); // 创建字节输入流
             FileOutputStream fos = new FileOutputStream(File.createTempFile("log4j2-", ".xml"));// 创建字节输出流)
        ) {
            byte[] bbuf = new byte[32];// 字节缓存数组
            int hasRead = 0;// 实际读取的字节数
            while ((hasRead = fis.read(bbuf)) > 0)
                fos.write(bbuf, 0, hasRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
