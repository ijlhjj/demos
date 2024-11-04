package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 中奖概率计算 <br />
 *
 * <pre>
 * 如果从n个数字中抽取k个数字，计算概率公式为：probability = 1 / ((n*(n-1)*(n-2)...*(n-k+1)) / (1*2*3...*k))
 *     例如：从50个数字中抽取6个数字，计算如下：
 *         probability = 1 / ((50*49*48*47*46*45) / (1*2*3*4*5*6)) = 1/15890700
 * </pre>
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class LotteryOdds {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int n = 50;
        int k = 6;

        int lotteryOdds = 1;
        for (int i = 1; i <= k; i++)
            lotteryOdds = lotteryOdds * (n - i + 1) / i;

        logger.info("中奖概率为： 1 / {}", lotteryOdds);
    }

}
