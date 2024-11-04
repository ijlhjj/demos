package com.sweetmanor.demo.io;

import com.sweetmanor.common.Const;

import java.io.FileReader;
import java.io.PushbackReader;

/**
 * 推回缓冲区示例 <br />
 * 当推回缓冲区的数据内容大于缓冲区大小时，将抛出异常
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class PushbackReaderDemo1 {

    public static void main(String[] args) {
        try (PushbackReader pr = new PushbackReader(new FileReader(Const.classPath + "log4j2.xml"), 64)) { // 创建一个推回缓冲区为64的PushbackReader对象
            char[] buffer = new char[32];
            String lastContent = "";// 用于保存上次读取的字符串内容
            int hasRead = 0;
            while ((hasRead = pr.read(buffer)) > 0) { // 循环读取文件内容
                String content = new String(buffer, 0, hasRead);
                int index = 0;
                // 如果上次内容和本次内容包含目标字符串
                if ((index = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                    pr.unread((lastContent + content).toCharArray());// 把两次内容一起推回缓存区
                    // 当index大于缓存字符数组的大小时，此处将报错java.lang.IndexOutOfBoundsException
                    pr.read(buffer, 0, index);// 读取目标之前的内容
                    System.out.print(new String(buffer, 0, index));
                } else {
                    System.out.print(lastContent);
                    lastContent = content;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
