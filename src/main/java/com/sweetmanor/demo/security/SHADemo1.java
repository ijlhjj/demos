package com.sweetmanor.demo.security;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * SHA信息摘要示例
 * 
 * @version 1.0 2016-06-16
 * @author wenz
 */
public class SHADemo1 {

	private MessageDigest sha;// SHA信息摘要对象

	public SHADemo1() {
		try {
			sha = MessageDigest.getInstance("SHA");// 创建SHA信息摘要对象
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算字符串SHA值
	 */
	public byte[] encrypt(String msg) {
		sha.update(msg.getBytes());// 更新摘要信息
		byte[] resultBytes = sha.digest();// 计算HASH结果
		return resultBytes;
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
			byte[] bytesOuput = sha.digest();// 计算Hash结果
			return Hex.encodeHexString(bytesOuput);// 调用方法将字节数组转换为十六进制字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		SHADemo1 demo = new SHADemo1();

		String msg = "明天又是新的一天！";
		System.out.println("明文是：" + msg);

		byte[] enc = demo.encrypt(msg);
		System.out.println("SHA信息摘要：" + new String(enc));
	}

}
