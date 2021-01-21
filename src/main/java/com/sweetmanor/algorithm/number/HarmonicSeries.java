package com.sweetmanor.algorithm.number;

/**
 * 计算调和级数：1 + 1/2 + 1/3 ...
 */
public class HarmonicSeries {

	public static void main(String[] args) {
		int num = 20;
		double sum = 0.0;// 使用double类型进行近似计算
		for (int i = 1; i <= num; i++)
			sum += 1.0 / i;
		System.out.println(sum);
	}

}
