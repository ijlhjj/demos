package com.sweetmanor.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 从进程读取数据示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class ReadFromProcessDemo1 {

    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("javac");// 创建进程运行javac命令
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));// 以p进程创建缓冲流
        String buffer = null;
        while ((buffer = br.readLine()) != null) {
            System.out.println(buffer);
        }
    }

}
