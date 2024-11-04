package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 计算调和级数：1 + 1/2 + 1/3 ...
 */
public class HarmonicSeries {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int num = 20;
        double sum = 0.0;// 使用double类型进行近似计算

        for (int i = 1; i <= num; i++)
            sum += 1.0 / i;

        logger.info(sum);
    }

}
