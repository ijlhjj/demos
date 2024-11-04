package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.FileReader;

/**
 * 文件字符输入流示例
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class FileReaderDemo1 {

    public static void main(String[] args) {
        try (FileReader fr = new FileReader(Const.classPath + "log4j2.xml");// 创建字符输入流
        ) {
            char[] cbuf = new char[64]; // 创建字符缓存数组
            int hasRead = 0;// 实际读取的字符数
            while ((hasRead = fr.read(cbuf)) > 0) { // 循环读取
                System.out.print(new String(cbuf, 0, hasRead));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
