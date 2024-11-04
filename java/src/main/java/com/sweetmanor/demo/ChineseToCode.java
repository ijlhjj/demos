package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * 汉字转区位码
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class ChineseToCode {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] codeBit = "汉".getBytes("GBK");// 此处应注意编码
        // 转换对应的区位码
        codeBit[0] -= (byte) 160;
        codeBit[1] -= (byte) 160;
        String code = convertNumber(codeBit[0]) + convertNumber(codeBit[1]); // 组合区位码
        logger.info(code);
    }

    // 数字转2位字符串
    private static String convertNumber(int num) {
        return String.format("%02d", num);// 数字的补零格式
    }

}
