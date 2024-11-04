package com.sweetmanor.demo.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * String类中各种方法的使用示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class StringDemo1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        testFormat();
        testSplit();
        logger.error("该软件没有买保险，请注意安全");
    }

    /**
     * format方法：格式化输出，可用于批量替换
     */
    public static void testFormat() {
        String formatArg = "本次抽取观众人员：\n\t%1$s\n恭喜%1$s成为本次观众抽奖的大奖得主。" + "\n\n我们将为%1$s颁发：\n\t过期的酸奶二十箱。";
        String info = String.format(formatArg, "诸葛亮");
        logger.info(info);
    }

    /**
     * split方法：字符串分割，分隔符使用正则表达式匹配
     */
    public static void testSplit() {
        // \\s：空白字符[ \t\n\x0B\f\r ]。注意：其中的反斜杠使用了2个
        String[] result = "this is a test".split("\\s");
        for (String s : result)
            logger.info(s);
    }

}
