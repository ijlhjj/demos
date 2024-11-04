package com.sweetmanor.demo.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符缓冲流示例
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class BufferedReaderDemo1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try (InputStreamReader reader = new InputStreamReader(System.in); // 系统标准输入流为字节流，用转换流将其包装成字符流
             BufferedReader br = new BufferedReader(reader);// 将字符流包装成缓冲流
        ) {
            String buffer = null;
            while ((buffer = br.readLine()) != null) { // 每次读取一行内容
                if (buffer.equals("exit")) {
                    System.exit(0);
                }
                logger.info("输入内容为： {}", buffer);
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
