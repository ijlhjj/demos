package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 用字符输出流输出所有Unicode字符。 <br />
 * Java中使用UTF-16描述CHAR类型 <br />
 * 汉字的判断使用[\u4e00-\u9fa5]
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class FileWriterDemo1 {

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter(Const.classPath + "Unicode.txt"))// 创建字符输出流
        {
            for (int i = 0; i < 65536; i++) { // 循环输出所有Unicode字符集
                char c = (char) i;
                fw.write(i + ":\t" + c + "\t" + Integer.toHexString(i) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
