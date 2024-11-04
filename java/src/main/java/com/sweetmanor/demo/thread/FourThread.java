package com.sweetmanor.demo.thread;

/**
 * 多线程示例：通过继承Thread类实现多线程
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FourThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;
    private int threadNum = ++threadCount;

    public FourThread() {
        System.out.println("Making " + threadNum);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread " + threadNum + "(" + countDown + ")");
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new FourThread().start();
        }
        System.out.println("All Thread Started!");
    }

}
