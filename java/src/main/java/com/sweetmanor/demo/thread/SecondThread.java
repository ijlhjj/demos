package com.sweetmanor.demo.thread;

/**
 * 多线程示例：通过实现Runnable接口实现多线程
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class SecondThread implements Runnable {

    private int i;// 多个线程可共享一个线程目标实例，当共享一个线程目标实例时，目标实例的实例属性是共享的

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                // 创建1个目标实例，并用它启动2个新线程
                SecondThread st = new SecondThread();
                new Thread(st, "新线程1").start();
                new Thread(st, "新线程2").start();
            }
        }
    }

}
