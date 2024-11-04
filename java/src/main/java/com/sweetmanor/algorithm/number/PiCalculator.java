package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * π的莱布尼茨公式 —— 《算法精粹》
 * <p>
 * π = 4/1 - 4/3 + 4/5 - 4/7 + 4/9 - 4/11 ……
 *
 * @author ijlhjj
 * @version 1.0 2023-12-09
 */
public class PiCalculator {
    private static final Logger logger = LogManager.getLogger();

    public static double calculatePi(int nTerms) {
        final double numerator = 4.0;
        double denominator = 1.0;
        double operation = 1.0;

        double pi = 0.0;
        for (int i = 0; i < nTerms; i++) {
            pi += operation * (numerator / denominator);
            denominator += 2.0;
            operation = -operation;
        }

        return pi;
    }

    public static void main(String[] args) {
        logger.info(calculatePi(1000000));
    }

}
