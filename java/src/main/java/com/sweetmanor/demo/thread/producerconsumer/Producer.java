package com.sweetmanor.demo.thread.producerconsumer;

/**
 * 生产者线程
 *
 * @author wenz
 * @version 1.0 2016-06-18
 */
public class Producer extends Thread {
    private Buffer buffer;
    private int number;

    public Producer(Buffer buffer, int number) {
        this.buffer = buffer;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; ; i++) {
            buffer.put(i);
            System.out.println("生产者" + number + "生产" + (i));
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
