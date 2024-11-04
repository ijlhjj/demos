package com.sweetmanor.demo.security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 信息验证码： <br />
 * 在生成信息摘要时，使用了一个密钥，只有同样的密钥才能生成同样的信息验证码。 <br />
 * 发送者和接收者共同拥有此密钥。
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class HMACMD5Demo1 {

    public static void main(String[] args) throws Exception {
        String msg = "消息验证码";
        System.out.println("明文是：" + msg);

        KeyGenerator keygen = KeyGenerator.getInstance("DESede");// 使用DESede生成验证码的密钥
        SecretKey key = keygen.generateKey();// 生成密钥
        byte[] keyByte = key.getEncoded();

        // 生成MAC对象
        SecretKeySpec SKS = new SecretKeySpec(keyByte, "HMACMD5");
        Mac mac = Mac.getInstance("HMACMD5");
        mac.init(SKS);

        mac.update(msg.getBytes("UTF8"));// 更新信息内容
        byte[] certifyCode = mac.doFinal();// 获取验证码
        System.out.println("密文是：" + new String(certifyCode));
    }

}
