package com.sweetmanor.demo.security;

import java.security.*;

/**
 * DSA非对称加密签名、认证过程
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class DSADemo1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] data = "明天又是新的一天！".getBytes();

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");// 非对称密钥生成器
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();// 生成密钥

        // 使用私钥进行加密
        Signature signature = Signature.getInstance(keyPairGen.getAlgorithm());
        signature.initSign(keyPair.getPrivate());
        signature.update(data);
        byte[] sign = signature.sign();

        // 使用公钥进行验密认证
        signature.initVerify(keyPair.getPublic());
        signature.update(data);
        boolean status = signature.verify(sign);

        System.out.println(status);
    }

}
