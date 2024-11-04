package com.sweetmanor.demo.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5信息摘要示例
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class MD5Demo1 {
    private static final Logger logger = LogManager.getLogger();

    private MessageDigest md5;// MD5信息摘要对象

    public MD5Demo1() {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
    }

    /**
     * 计算字符串MD5值
     */
    public byte[] encrypt(String msg) {
        md5.update(msg.getBytes());// 更新摘要信息
        return md5.digest();
    }

    /**
     * 计算文件MD5值
     */
    public String encrypt(File file) {
        try (FileInputStream fis = new FileInputStream(file); //
             FileChannel channel = fis.getChannel();) {
            ByteBuffer bBuffer = ByteBuffer.allocate(1024);
            // 循环读取文件内容
            while (channel.read(bBuffer) != -1) {
                bBuffer.flip();// 翻转缓冲区，在准备从缓冲区中读取数据时调用flip方法
                md5.update(bBuffer);// 更新计算摘要内容
                bBuffer.clear();// 清空缓冲区，在重新写缓冲区时调用clear方法
            }
            byte[] bytesOuput = md5.digest();// 计算Hash结果
            return Hex.encodeHexString(bytesOuput);// 调用方法将字节数组转换为十六进制字符串
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    public static void main(String[] args) {
        MD5Demo1 demo = new MD5Demo1();

        String msg = "明天又是新的一天！";
        logger.info("明文是：{}", msg);

        byte[] enc = demo.encrypt(msg);
        logger.info("MD5字节数组：{}", Arrays.toString(enc));
        logger.info("MD5信息摘要：{}", new String(enc));
    }

}
