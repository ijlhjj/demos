package com.sweetmanor.demo.thread;

/**
 * 多线程示例：通过继承Thread类实现多线程
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FirstThread extends Thread {

    private int i; // 每创建一个线程都是一个新的对象，不能共享实例属性

    // 重写run方法
    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                // 启动2个新线程
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }

}
