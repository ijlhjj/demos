package com.sweetmanor.algorithm.number;

/**
 * 数学计算示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class MathDemo {

    /**
     * 求最大公约数函数。前置条件：m、n是大于0的正整数
     */
    public static int gcd(int m, int n) {
        if (m % n == 0)
            return n;
        else
            return gcd(n, m % n);
    }

    /**
     * 计算数组元素的平均值
     */
    public static double avg(int[] a) {
        int len = a.length;
        double sum = 0.0;
        for (int j : a)
            sum += j;
        return sum / len;
    }

    /**
     * 复制数组
     */
    public static void copyArray(int[] fromArray, int[] toArray) {
        int len = fromArray.length;
        System.arraycopy(fromArray, 0, toArray, 0, len);
    }

    /**
     * 反转数组元素
     */
    public static void reversalArray(int[] a) {
        int len = a.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = a[i];
            a[i] = a[len - 1 - i];
            a[len - 1 - i] = temp;
        }
    }

    /**
     * 素数判断方法
     */
    public static boolean isPrime(int n) {
        if (n < 2) // 小于2的数直接返回false
            return false;
        double last = Math.sqrt(n);
        for (int i = 2; i <= last; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    /**
     * 牛顿迭代法求平方根
     */
    public static double sqrt(double c) {
        if (c < 0)
            return Double.NaN;
        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c / t) > err * t)
            t = (c / t + t) / 2.0;
        return t;
    }

    /**
     * 高效的幂运算
     *
     * @param x 底数
     * @param n 指数
     */
    public static long pow(long x, int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;

        if (n % 2 == 0)
            return pow(x * x, n / 2);
        else
            return pow(x * x, n / 2) * x;
    }

}
