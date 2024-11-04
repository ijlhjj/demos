package com.sweetmanor.demo.security;

import java.util.Random;

/**
 * XOR加密 —— 《算法精粹》
 * <p>
 * A ^ B = C
 * C ^ B = A
 * C ^ A = B
 *
 * @author ijlhjj
 * @version 1.0 2023-12-09
 */
public class UnbreakableEncryption {

    record KeyPair(byte[] key1, byte[] key2) {
    }

    /**
     * 生成等长的随机假数据
     */
    private static byte[] randomKey(int length) {
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

    /**
     * 加密
     */
    public static KeyPair encrypt(String original) {
        byte[] originalBytes = original.getBytes();
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedKey = new byte[originalBytes.length];
        for (int i = 0; i < originalBytes.length; i++) {
            encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]); //XOR
        }
        return new KeyPair(dummyKey, encryptedKey);
    }

    /**
     * 解密
     */
    public static String decrypt(KeyPair keyPair) {
        byte[] decrypted = new byte[keyPair.key1.length];
        for (int i = 0; i < keyPair.key1.length; i++) {
            decrypted[i] = (byte) (keyPair.key1[i] ^ keyPair.key2[i]); //XOR
        }
        return new String(decrypted);
    }

    public static void main(String[] args) {
        KeyPair kp = encrypt("One Time Pad!");
        String result = decrypt(kp);
        System.out.println(result);
    }

}
