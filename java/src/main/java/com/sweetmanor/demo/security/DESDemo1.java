package com.sweetmanor.demo.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * DES对称加密示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class DESDemo1 {

    public static void main(String[] args) throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("DES");// DES对称密钥生成器
        SecretKey desKey = keygen.generateKey();// 生成对称密钥
        Cipher cipher = Cipher.getInstance("DES");// 创建加密、解密工具对象

        String msg = "明天又是新的一天！";
        System.out.println("明文是：" + msg);

        cipher.init(Cipher.ENCRYPT_MODE, desKey);// 加密模式
        byte[] enc = cipher.doFinal(msg.getBytes());// 进行加密操作
        System.out.println("密文是：" + new String(enc));

        cipher.init(Cipher.DECRYPT_MODE, desKey);// 解密模式
        byte[] dec = cipher.doFinal(enc);// 进行解密操作
        System.out.println("解密后：" + new String(dec));
    }

}
