package com.sweetmanor.demo.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

/**
 * MD5信息摘要示例
 * 
 * @version 1.0 2016-06-16
 * @author wenz
 */
public class MD5Demo1 {

	private MessageDigest md5;// MD5信息摘要对象

	public MD5Demo1() {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算字符串MD5值
	 */
	public byte[] encrypt(String msg) {
		md5.update(msg.getBytes());// 更新摘要信息
		byte[] resultBytes = md5.digest();// 计算HASH结果
		return resultBytes;
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
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		MD5Demo1 demo = new MD5Demo1();

		String msg = "明天又是新的一天！";
		System.out.println("明文是：" + msg);

		byte[] enc = demo.encrypt(msg);
		System.out.println("MD5字节数组：" + Arrays.toString(enc));
		System.out.println("MD5信息摘要：" + new String(enc));
	}

}
