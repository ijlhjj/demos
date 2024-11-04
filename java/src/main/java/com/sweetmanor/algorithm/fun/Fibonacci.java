package com.sweetmanor.algorithm.fun;

/**
 * 斐波那契数列 <br />
 * <code>
 * F(1) = 1
 * F(2) = 1
 * F(n) = F(n-1) + F(n-2)  (n ≥ 3，n ∈ N*)
 * </code>
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class Fibonacci {

    /**
     * 递归实现：这是一个反例，运行时间以指数级速度增长。 (3/2)^N <= fib(N) < (5/3)^N
     */
    public static long fib1(int n) {
        assert n > 0;

        if (n <= 2)
            return 1;
        else
            return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 数组实现：线性运行时间 O(N)
     */
    public static long fib2(int n) {
        assert n > 0;
        if (n <= 2)
            return 1;

        long[] arr = new long[n];
        arr[0] = 1;// F(1)
        arr[1] = 1;// F(2)
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];// F(N)
        }

        return arr[n - 1];
    }

    /**
     * 双数值计算：线性运行时间 O(N)
     */
    public static long fib3(int n) {
        assert n > 0;
        if (n <= 2)
            return 1;

        long f1 = 1, f2 = 1;// 基准值
        double count = n / 2.0;// 循环次数，每次计算两个值，所以循环次数减半
        for (int i = 1; i < count; i++) {
            f1 = f1 + f2;
            f2 = f1 + f2;
        }

        return n % 2 == 1 ? f1 : f2;
    }

    public static void main(String[] args) {
        final int N = 20;// 输出斐波那契数列中的前 N 个值

        System.out.println("斐波那契数列：");

        // 递归：40以内可以秒出
        for (int i = 1; i <= N; i++)
            System.out.print(fib1(i) + "  ");
        System.out.println();

        // 数组
        for (int i = 1; i <= N; i++)
            System.out.print(fib2(i) + "  ");
        System.out.println();

        // 双值计算
        for (int i = 1; i <= N; i++)
            System.out.print(fib3(i) + "  ");
        System.out.println();
    }

}
