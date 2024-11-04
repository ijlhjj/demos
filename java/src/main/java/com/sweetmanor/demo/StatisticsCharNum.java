package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * 字符统计示例 <br />
 * <p>
 * 输入一行字符，分别统计其中英文字母、空格、数字和其它字符的个数。 <br />
 * Java中使用UTF-16描述char类型 <br />
 * 汉字的判断使用[\u4e00-\u9fa5]
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class StatisticsCharNum {
    private static final Logger logger = LogManager.getLogger();

    // 统计字符串中的英文字母、空格、数字和其它字符的个数
    // 如果输入中文，当其他字符处理
    public static void countCharNum(String text) {
        int cNum = 0; // 英文字符个数
        int sNum = 0;// 空格个数
        int nNum = 0;// 数字个数
        int oNum = 0;// 其他字符个数

        char[] cArr = text.toCharArray();
        for (char c : cArr) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
                cNum++;
            else if (c == ' ')
                sNum++;
            else if (c >= '0' && c <= '9')
                nNum++;
            else
                oNum++;
        }

        logger.info("字符串中包含：英文字符 {} 个，空格 {} 个，数字 {} 个，其他字符 {} 个。", cNum, sNum, nNum, oNum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = null;
        while ((text = sc.nextLine()) != null) {
            if ("exit".equals(text))
                System.exit(0);
            countCharNum(text);// 统计字符数
        }
        sc.close();
    }

}
