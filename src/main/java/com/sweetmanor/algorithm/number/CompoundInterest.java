package com.sweetmanor.algorithm.number;

/**
 * 投资收益复利计算
 * 
 * @version 1.0 2020-01-12
 * @author ijlhjj
 */
public class CompoundInterest {

	public static void main(String[] args) {
		final double STARTRATE = 10;
		final int NRATES = 6;
		final int NYEARS = 10;

		// 设置收益率初始值：10% - 15%
		double[] interestRate = new double[NRATES];
		for (int i = 0; i < interestRate.length; i++)
			interestRate[i] = (STARTRATE + i) / 100.0;

		double[][] balances = new double[NYEARS][NRATES];// 二维数组存储10行，每行6列

		// 设置初始投资：10000
		for (int i = 0; i < balances[0].length; i++)
			balances[0][i] = 10000;

		// 收益复利计算
		for (int i = 1; i < balances.length; i++) {
			for (int j = 0; j < balances[i].length; j++) {
				double oldBalance = balances[i - 1][j]; // 上一行的值
				balances[i][j] = oldBalance + oldBalance * interestRate[j]; // 计算收益
			}
		}

		// 打印标题收益率行
		for (int j = 0; j < interestRate.length; j++)
			System.out.printf("%9.0f%%", 100 * interestRate[j]);

		System.out.println();

		// 打印每年的收益
		for (double[] row : balances) {
			for (double b : row)
				System.out.printf("%10.2f", b);
			System.out.println();
		}
	}

}
