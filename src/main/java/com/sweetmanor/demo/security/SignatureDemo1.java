package com.sweetmanor.demo.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 使用SHA和RSA实现数字签名
 * 
 * @version 1.0 2016-06-16
 * @author wenz
 */
public class SignatureDemo1 {

	public static void main(String[] args) throws Exception {
		String msg = "明天又是新的一天！";
		System.out.println("原文是：" + msg);
		byte[] msgBytes = msg.getBytes();

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();// 生成密钥对

		Signature sign = Signature.getInstance("SHA1WithRSA");// 指定用SHA1和RSA创建数字签名对象
		PrivateKey privateKey = keyPair.getPrivate();// 获取私钥，用私钥进行签名
		sign.initSign(privateKey);// 用私钥初始化签名操作
		sign.update(msgBytes);// 更新签名内容
		byte[] signBytes = sign.sign();// 进行签名操作，得到结果数组
		System.out.println("签名是：" + new String(signBytes));

		PublicKey publicKey = keyPair.getPublic();// 获取公钥，用公钥进行验证
		sign.initVerify(publicKey);// 用公钥初始化验证操作
		sign.update(msgBytes);// 再次更新签名内容

		if (sign.verify(signBytes)) {// 进行验证，输出验证信息
			System.out.println("签名验证成功");
		} else {
			System.out.println("签名验证失败");
		}
	}

}
