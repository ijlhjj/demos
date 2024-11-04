package com.sweetmanor.demo.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA信息摘要示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class SHADemo1 {
    private static final Logger logger = LogManager.getLogger();

    private MessageDigest sha;// SHA信息摘要对象

    public SHADemo1() {
        try {
            sha = MessageDigest.getInstance("SHA");// 创建SHA信息摘要对象
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
    }

    /**
     * 计算字符串SHA值
     */
    public byte[] encrypt(String msg) {
        sha.update(msg.getBytes());// 更新摘要信息
        return sha.digest();
    }

    /**
     * 计算文件SHA值
     */
    public String encrypt(File file) {
        try (FileInputStream fis = new FileInputStream(file); //
             FileChannel channel = fis.getChannel();) {
            ByteBuffer bBuffer = ByteBuffer.allocate(1024);
            while (channel.read(bBuffer) != -1) { // 循环读取文件内容
                bBuffer.flip();
                sha.update(bBuffer);// 更新计算摘要内容
                bBuffer.clear();
            }
            byte[] bytesOutput = sha.digest();// 计算Hash结果
            return Hex.encodeHexString(bytesOutput);// 调用方法将字节数组转换为十六进制字符串
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public static void main(String[] args) {
        SHADemo1 demo = new SHADemo1();

        String msg = "明天又是新的一天！";
        logger.info("明文是：{}", msg);


        byte[] enc = demo.encrypt(msg);
        logger.info("SHA信息摘要：{}", new String(enc));
    }

}
