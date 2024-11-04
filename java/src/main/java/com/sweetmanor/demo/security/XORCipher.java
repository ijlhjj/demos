package com.sweetmanor.demo.security;

/**
 * 使用异或方式进行加密 <br />
 * 对字符进行位异或加密，速度快。 <br />
 * 两次异或等于原数，与不使用第三个变量完成两数交换同理。
 *
 * @author wenz
 * @version 1.0 2016-06-16
 */
public class XORCipher {
    private static final int KEY = Character.MAX_VALUE;

    /**
     * 字符数组加密方法
     *
     * @param text 明文
     * @return 密文
     */
    public static char[] encode(char[] text) {
        char[] array = new char[text.length];
        for (int i = 0; i < text.length; i++) {
            array[i] = (char) (text[i] ^ KEY);
        }
        return array;
    }

    /**
     * 字符数组解密方法
     *
     * @param text 密文
     * @return 明文
     */
    public static char[] decode(char[] text) {
        return encode(text);// 位加密是对称的，所以对密文的加密等于解密
    }

    public static void main(String[] args) {
        char[] text = "明天又是新的一天！".toCharArray();
        char[] result = encode(text);

        System.out.println(new String(result));
        System.out.println(new String(decode(result)));
    }

}
