package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 分解质因子 <br />
 * 例如：输入90,打印出 90 = 2 * 3 * 3 * 5。
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class PrimeFactor {
    private static final Logger logger = LogManager.getLogger();

    /**
     * 分解质因数方法:
     *
     * <pre>
     * 		对n分解质因数，步骤如下：
     * 			(1)如果n为质数，则分解完成;
     * 			(2)如果n能被素数k整除，则k是一个质因子。n=n/k，转到步骤1;
     * 			(3)如果n不能被k整除，k=k+1，转到步骤1。
     * </pre>
     *
     * @param num    待分解质因子的数
     * @param result 存放分解结果，必须在调用前初始化
     */
    public static void primeFactor(int num, List<Integer> result) {
        if (PrimeNumber.isPrime(num)) {
            result.add(num); // num为素数，分解完成
        } else {
            double end = Math.sqrt(num);
            for (int i = 2; i <= end; i++) // 在可能的质因子范围内循环
                if (PrimeNumber.isPrime(i) && (num % i == 0)) { // 是素数，能整除，则找到一个质因子
                    result.add(i);
                    primeFactor(num / i, result);// 递归分解质因子
                    break;// 找到最小的质因子后，不需要再循环其余的值
                }
        }
    }

    public static void main(String[] args) {
        logger.info("分解质因子：");

        int num = 90;// 要分解的数
        List<Integer> primeFactorList = new ArrayList<>();
        primeFactor(num, primeFactorList);// 调用分解质因子方法

        logger.info("{} = {}", num, primeFactorList);
    }

}
