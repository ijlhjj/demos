package com.sweetmanor.demo.thread.producerconsumer;

/**
 * 模拟生产者、消费者模式
 * 
 * @version 1.0 2016-06-18
 * @author wenz
 */
public class Main {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();// 模拟消费队列
		new Producer(buffer, 101).start();
		new Consumer(buffer, 201).start();// 3个消费者，将随机消费生产的数据
		new Consumer(buffer, 202).start();
		new Consumer(buffer, 203).start();
	}

}
