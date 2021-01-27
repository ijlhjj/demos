package com.sweetmanor.demo.collection;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList方法示例 <br />
 * 
 * 返回内容可变但大小不可变集合。也就是说，可以在这个列表上调用set方法，但不能调用add或remove方法 <br />
 * Java9新引入的List.of方法返回内容和大小均不可修改的集合
 * 
 * @author ijlhjj
 */
public class FixedSizeList {

	public static void main(String[] args) {
		List<String> fixedList = Arrays.asList("疯狂Java讲义", "疯狂Java EE企业应用实战");
		System.out.println(fixedList.getClass());// 返回Arrays的内部类ArrayList，与java.util.ArrayList不同

		for (int i = 0; i < fixedList.size(); i++) {
			System.out.println(fixedList.get(i));
		}

		/* 调用以下方法会报 UnsupportedOperationException */
		// fixedList.add("疯狂android讲义");
		// fixedList.remove("疯狂Java讲义");
	}

}
