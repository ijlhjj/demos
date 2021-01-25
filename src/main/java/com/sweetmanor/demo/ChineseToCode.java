package com.sweetmanor.demo;

import java.io.UnsupportedEncodingException;

/**
 * 汉字转区位码
 * 
 * @version 1.0 2016-06-14
 * @author wenz
 */
public class ChineseToCode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] codeBit = "汉".getBytes("GBK");// 此处应注意编码
		// 转换对应的区位码
		codeBit[0] -= 160;
		codeBit[1] -= 160;
		String code = convertNumber(codeBit[0]) + convertNumber(codeBit[1]); // 组合区位码
		System.out.println(code);
	}

	// 数字转2位字符串
	private static String convertNumber(int num) {
		return String.format("%02d", num);// 数字的补零格式
	}

}
