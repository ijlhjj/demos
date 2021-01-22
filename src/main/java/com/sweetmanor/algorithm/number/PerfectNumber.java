package com.sweetmanor.algorithm.number;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 完全数 <br />
 * 如果一个数恰好等于它的因子（真约数）之和，则称该数为“完全数”。 <br />
 * 打印1000以内的完全数。 <br />
 * 真约数：列出某数的约数，去掉该数本身，剩下的就是它的真约数，包括1
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class PerfectNumber {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * 完数判断方法
	 */
	public static boolean isPerfect(int num) {
		List<Integer> factors = getFactor(num);// 获取真因子

		int sum = 0;
		for (Integer i : factors)
			sum += i;

		if (sum == num) { // 符合完数规则
			logger.debug(num + ":\t" + factors);
			return true;
		}

		return false;
	}

	/**
	 * 获取所有真因子
	 */
	public static List<Integer> getFactor(int num) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= num / 2; i++)
			if (num % i == 0)
				result.add(i);

		return result;
	}

	public static void main(String[] args) {
		int count = 0;
		logger.info("1000以内的完数：");
		for (int i = 2; i <= 1000; i++)
			if (isPerfect(i))
				count++;

		logger.info("1000以内有 " + count + " 个完数");
	}

}
