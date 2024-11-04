package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * 计算1 + 1/2! + 1/3! ··· 1/20!
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class FactorialSum {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        BigDecimal sum = BigDecimal.ZERO; // 结果和
        BigDecimal factorial = BigDecimal.ONE; // 阶乘项的计算结果

        for (int i = 2; i <= 20; i++) {
            sum = sum.add(factorial); // 累加各项阶乘的和
            factorial = factorial.multiply(BigDecimal.valueOf(1.0 / i)); // 计算阶乘项
        }

        logger.info("1 + 1/2! + 1/3! ··· 1/20!的计算结果等于：\n{}", sum);
    }

}
