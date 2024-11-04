package com.sweetmanor.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 多线程示例：通过实现Callable接口实现多线程
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class ThirdThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
        return i;
    }

    public static void main(String[] args) {
        ThirdThread tt = new ThirdThread();
        FutureTask<Integer> task = new FutureTask<>(tt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                new Thread(task, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
