package com.sweetmanor.algorithm.functional;

import com.sweetmanor.common.RandomInt;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * 最大子序列和问题：摘自《数据结构与算法分析》</br>
 * 给定（可能有负的）整数序列A1, A2,...,AN, 求 Sum(Ak)(k>=i, k<=j)的最大值。</br>
 * 如果所有的整数都是负的，那么连续子序列的最大和是那个最大的负数项。
 *
 * @author ijlhjj
 * @version 1.0 2020-04-04
 */
public class MaxSubsequence {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        int[] array = RandomInt.random(10, -100, 100);
        logger.info("测试数组：");
        String arrStr = Arrays.toString(array);
        logger.info(arrStr);

        logger.info("最大子序列和：");
        int maxSum1 = maxSubSum1(array);// 数组大小上限：10_000
        logger.info("结果1：{}", maxSum1);

        int maxSum2 = maxSubSum2(array);// 数组大小上限：1_000_000
        logger.info("结果2：{}", maxSum2);

        int maxSum3 = maxSubSum3(array, 0, array.length - 1);
        logger.info("结果3：{}", maxSum3);

        int maxSum4 = maxSubSum4(array);
        logger.info("结果4：{}", maxSum4);
    }

    /**
     * 穷举法：时间复杂度 O(N^3)
     */
    public static int maxSubSum1(int[] array) {
        int maxSum = 0;

        for (int i = 0; i < array.length; i++)
            for (int j = i; j < array.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++)
                    thisSum += array[k];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }

        return maxSum;
    }

    /**
     * 改进算法：避免重复计算以减少运行时间。时间复杂度 O(N^2)
     */
    public static int maxSubSum2(int[] array) {
        int maxSum = 0;

        for (int i = 0; i < array.length; i++) {
            int thisSum = 0;
            for (int j = i; j < array.length; j++) {
                thisSum += array[j];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }

        return maxSum;
    }

    /**
     * 分治算法：递归求解半数子序列，最后进行一次合并。时间复杂度 O(N logN)
     */
    public static int maxSubSum3(int[] array, int left, int right) {
        // 基准情况
        if (left == right)
            return Math.max(array[left], 0);

        int center = (left + right) / 2;
        int maxLeftSum = maxSubSum3(array, left, center);
        int maxRightSum = maxSubSum3(array, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += array[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += array[i];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }

        return NumberUtils.max(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    /**
     * 最优算法（联机算法：仅需要常量空间并以线性时间运行的联机算法几乎是完美的算法）。时间复杂度 O(N)</br>
     * 此算法基于一个假设：任何负的子序列不可能是最优子序列的前缀。
     */
    public static int maxSubSum4(int[] array) {
        int maxSum = 0, thisSum = 0;

        for (int j : array) {
            thisSum += j;

            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }

        return maxSum;
    }

}
