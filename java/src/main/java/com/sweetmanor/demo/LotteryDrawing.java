package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

/**
 * 彩票抽奖示例 <br />
 * 例如21选3的抽奖模式
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class LotteryDrawing {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int n = 21;
        int k = 3;

        // 初始化号码池
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i + 1;

        // 随机选取中奖号码
        Random random = new Random();
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int r = random.nextInt(n);// 生成随机索引
            result[i] = numbers[r];
            numbers[r] = numbers[n - 1];// 移除已抽取号码
            n--;
        }

        // 输出中奖结果
        Arrays.sort(result);
        logger.info("中奖号码为：");
        for (int num : result)
            logger.info(num);
    }

}
