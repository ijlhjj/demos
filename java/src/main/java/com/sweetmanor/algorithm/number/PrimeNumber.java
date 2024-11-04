package com.sweetmanor.algorithm.number;

/**
 * 素数判断：判断并输出101-200之间的素数
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class PrimeNumber {

    /**
     * 素数判断方法
     */
    public static boolean isPrime(int num) {
        if (num < 2)
            return false;

        double last = Math.sqrt(num);
        for (int i = 2; i <= last; i++)
            if (num % i == 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println("101-200之间的素数：");

        int count = 0;

        for (int i = 101; i < 200; i++) {
            if (isPrime(i)) {
                System.out.print(i + "\t");
                if (++count % 10 == 0)
                    System.out.println();
            }
        }

        System.out.println("\n101-200之间有 " + count + " 个素数");
    }

}
