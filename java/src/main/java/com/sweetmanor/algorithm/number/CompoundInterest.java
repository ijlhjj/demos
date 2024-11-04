package com.sweetmanor.algorithm.number;

import java.util.Arrays;

/**
 * 投资收益复利计算
 *
 * @author ijlhjj
 * @version 1.0 2020-01-12
 */
public class CompoundInterest {

    public static void main(String[] args) {
        final double START_RATE = 10;
        final int N_RATES = 6;
        final int N_YEARS = 10;

        // 设置收益率初始值：10% - 15%
        double[] interestRate = new double[N_RATES];
        for (int i = 0; i < interestRate.length; i++)
            interestRate[i] = (START_RATE + i) / 100.0;

        double[][] balances = new double[N_YEARS][N_RATES];// 二维数组存储10行，每行6列

        // 设置初始投资：10000
        Arrays.fill(balances[0], 10000);

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
