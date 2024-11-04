package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 不使用中间变量，实现两个整数的值交换
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class SwapWithoutTemp {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        String message = "a = {}, b = {}";

        logger.info("交换之前：");
        logger.info(message, a, b);

        // 按位异或进行交换
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        logger.info("交换之后：");
        logger.info(message, a, b);

        // 求和进行交换
        a = a + b;
        b = a - b;
        a = a - b;

        logger.info("两次交换之后：");
        logger.info(message, a, b);
    }

}
