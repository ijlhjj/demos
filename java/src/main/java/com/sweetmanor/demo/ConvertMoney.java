package com.sweetmanor.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

/**
 * 大写金额转换示例 <br />
 * 摘抄示例仅供参考，需调试方可实际使用
 */
public class ConvertMoney {
    private static final Logger logger = LogManager.getLogger();

    private static final String[] STR_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};// 大写数字
    private static final String[] STR_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};// 整数单位
    private static final String[] STR_UNIT2 = {"厘", "分", "角"};// 小数单位

    // 处理金额整数部分
    public static String getInteger(String num) {
        if (num.contains(".")) { // 判断是否包含小数点
            num = num.substring(0, num.indexOf("."));
        }

        num = StringUtils.reverse(num); // 反转字符串以加入单位
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            temp.append(STR_UNIT[i]);
            temp.append(STR_NUMBER[num.charAt(i) - 48]);
        }
        num = temp.reverse().toString();

        // 替换字符串的字符
        num = num.replace("零拾", "零");
        num = num.replace("零佰", "零");
        num = num.replace("零仟", "零");
        num = num.replace("零万", "万");
        num = num.replace("零亿", "亿");
        num = num.replace("零零", "零");
        num = num.replace("亿万", "亿");

        if (num.lastIndexOf("零") == num.length() - 1) { // 如果字符串以零结尾将其除去
            num = num.substring(0, num.length() - 1);
        }

        return num;
    }

    // 处理金额的小数部分
    public static String getDecimal(String num) {
        if (!num.contains(".")) { // 判断是否包含小数点
            return "";
        }
        num = num.substring(num.indexOf(".") + 1);

        num = StringUtils.reverse(num); // 反转字符串以加入单位
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            temp.append(STR_UNIT2[i]);
            temp.append(STR_NUMBER[num.charAt(i) - 48]);
        }
        num = temp.reverse().toString();

        // 替换字符串的字符
        num = num.replace("零角", "零");
        num = num.replace("零分", "零");
        num = num.replace("零厘", "零");
        num = num.replace("零零", "零");

        if (num.lastIndexOf("零") == num.length() - 1) { // 如果字符串以零结尾将其除去
            num = num.substring(0, num.length() - 1);
        }

        return num;
    }

    // 转换成大写金额
    public static String convert(double d) {
        DecimalFormat df = new DecimalFormat("#0.###");
        String strNum = df.format(d); // 格式化double数字

        if (strNum.contains(".")) { // 整数单位只支持整数部分在12位以内
            String num = strNum.substring(0, strNum.indexOf("."));
            if (num.length() > 12) {
                logger.info("数字超出可转换范围！");
                return "";
            }
        }

        String point = "";// 小数点
        if (strNum.contains(".")) {
            point = "元";
        } else {
            point = "元整";
        }

        String result = getInteger(strNum) + point + getDecimal(strNum); // 转换结果
        if (result.startsWith("元")) { // 判断金额是否不包含整数部分
            result = result.substring(1);
        }

        return result;
    }

    public static void main(String[] args) {
        String money = convert(123456789.1234);
        logger.info(money);
    }

}
