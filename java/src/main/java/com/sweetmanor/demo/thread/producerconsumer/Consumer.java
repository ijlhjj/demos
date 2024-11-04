package com.sweetmanor.demo.thread.producerconsumer;

/**
 * 消费者线程
 *
 * @author wenz
 * @version 1.0 2016-06-18
 */
public class Consumer extends Thread {
    private Buffer buffer;
    private int number;

    public Consumer(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            int v = buffer.get();
            System.out.println("消费者" + number + "消费" + v);
        }
    }

}
