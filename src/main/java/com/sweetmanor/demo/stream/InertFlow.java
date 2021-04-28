package com.sweetmanor.demo.stream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 惰性流：<br />
 * 
 * 流是惰性的，在达到终止条件前不会处理元素，达到终止条件后只处理所需的值。
 * 
 * @version 1.0 2021-04-28
 * @author wenz
 */
public class InertFlow {

	public static void main(String[] args) {
		new InertFlow().run();
	}

	public void run() {
		OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200) //
				.map(this::multByTwo) // 对元素倍增
				.filter(this::divByThree) // 模3过滤
				.findFirst();
		System.out.println(firstEvenDoubleDivBy3);
	}

	// 倍增方法
	public int multByTwo(int n) {
		System.out.println("初始值（乘2以前）：\t\t" + n);
		return n * 2;
	}

	// 对3取模方法
	public boolean divByThree(int n) {
		System.out.println("倍增后（乘2以后，模3以前）：\t" + n);
		return n % 3 == 0;
	}

}
