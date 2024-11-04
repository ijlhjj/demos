package com.sweetmanor.demo.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 凯撒密码示例
 *
 * @author ijlhjj
 * @version 1.0 2016-06-15
 */
public class CaesarCipher {

    /**
     * 字节数组加密方法
     *
     * @param input 明文
     * @return 密文
     */
    public static byte[] encode(byte[] input) {
        byte[] output = new byte[input.length];// 创建密文数组
        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (input[i] + 3); // 对每一字节进行加3处理
        }
        return output;
    }

    /**
     * 字节数组解密方法
     *
     * @param input 密文
     * @return 明文
     */
    public static byte[] decode(byte[] input) {
        byte[] output = new byte[input.length];// 创建明文数组
        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (input[i] - 3);// 对每一字节进行减3处理
        }
        return output;
    }

    /**
     * 加密、解密标识
     */
    enum Flag {
        ENCODE, DECODE
    }

    /**
     * 文件加密：加密后文件在源文件目录以" 源文件名 + .caesar "命名
     *
     * @param sourcePath 源文件路径
     */
    public static void encode(String sourcePath) {
        encode(new File(sourcePath), new File(sourcePath + ".caesar"));
    }

    /**
     * 文件加密
     *
     * @param source 源文件
     * @param target 加密文件
     */
    public static void encode(File source, File target) {
        doFinal(source, target, Flag.ENCODE); // 调用实际执行的方法
    }

    /**
     * 文件解密：解密后文件在源文件目录以" 源文件名 + .d "命名
     *
     * @param sourcePath 源文件路径
     */
    public static void decode(String sourcePath) {
        decode(new File(sourcePath), new File(sourcePath + ".d"));
    }

    /**
     * 文件解密
     *
     * @param source 源文件
     * @param target 解密文件
     */
    public static void decode(File source, File target) {
        doFinal(source, target, Flag.DECODE);// 调用实际执行的方法
    }

    /**
     * 实际执行文件加密、解密方法
     *
     * @param source 源文件
     * @param target 目标文件
     * @param flag   加密、解密标识
     */
    private static void doFinal(File source, File target, Flag flag) {
        try (FileInputStream fis = new FileInputStream(source); // 创建密文输入流
             FileOutputStream fos = new FileOutputStream(target); // 创建明文输出流
        ) {
            FileChannel inChannel = fis.getChannel(); // 获取文件输入流通道
            FileChannel outChannel = fos.getChannel();// 获取文件输出流通道

            ByteBuffer bBuffer = ByteBuffer.allocate(1024);// 创建一个1024字节的缓冲区
            ByteBuffer oBuffer = null;
            // 循环读取文件内容
            while (inChannel.read(bBuffer) != -1) {
                bBuffer.flip();// 翻转缓冲区，在准备从缓冲区中读取数据时调用flip方法

                // 调用字节数组加密、解密方法
                if (flag == Flag.ENCODE)
                    oBuffer = ByteBuffer.wrap(encode(bBuffer.array()));
                else if (flag == Flag.DECODE)
                    oBuffer = ByteBuffer.wrap(decode(bBuffer.array()));

                outChannel.write(oBuffer);// 把ByteBuffer中的数据写入输出通道

                oBuffer.clear();// oBuffer没有重复用，所以不需要调用flip方法
                bBuffer.clear();// 清空缓冲区，在重新写缓冲区时调用clear方法
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        encode("");
        decode("");
        System.out.println("完成");
    }

}
