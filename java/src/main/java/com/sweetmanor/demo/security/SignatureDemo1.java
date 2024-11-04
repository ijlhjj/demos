package com.sweetmanor.demo.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.*;

/**
 * 使用SHA和RSA实现数字签名
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class SignatureDemo1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        String msg = "明天又是新的一天！";
        logger.info("原文是：{}", msg);
        byte[] msgBytes = msg.getBytes();

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();// 生成密钥对

        Signature sign = Signature.getInstance("SHA1WithRSA");// 指定用SHA1和RSA创建数字签名对象
        PrivateKey privateKey = keyPair.getPrivate();// 获取私钥，用私钥进行签名
        sign.initSign(privateKey);// 用私钥初始化签名操作
        sign.update(msgBytes);// 更新签名内容
        byte[] signBytes = sign.sign();// 进行签名操作，得到结果数组
        logger.info("签名是：{}", new String(signBytes));

        PublicKey publicKey = keyPair.getPublic();// 获取公钥，用公钥进行验证
        sign.initVerify(publicKey);// 用公钥初始化验证操作
        sign.update(msgBytes);// 再次更新签名内容

        if (sign.verify(signBytes)) {// 进行验证，输出验证信息
            logger.info("签名验证成功");
        } else {
            logger.info("签名验证失败");
        }
    }

}
