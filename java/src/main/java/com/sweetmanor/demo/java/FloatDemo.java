package com.sweetmanor.demo.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 浮点数使用的注意事项：浮点数值不适用于无法接受舍入误差的金融计算
 *
 * @author ijlhjj
 * @version 1.0 2020-05-18
 */
public class FloatDemo {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info(2.0 - 1.1);// 二进制无法精确表示 1/10，由于舍入误差，输出可能不是期望的结果
        for (double x = 0; x != 10; x += 0.1) {// 由于精度问题，此循环将无法结束
            logger.info(x);
            if (x > 11)
                break;
        }
    }
}
