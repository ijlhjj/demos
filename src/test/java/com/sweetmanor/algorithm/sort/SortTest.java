package com.sweetmanor.algorithm.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import com.sweetmanor.common.RandomInt;

class SortTest {
	private int[] array;
	private int[] sortArray;

	@BeforeEach
	void setUp() throws Exception {
		array = RandomInt.random();
		sortArray = Arrays.copyOf(array, array.length);
	}

	@AfterEach
	void tearDown() throws Exception {
		Arrays.sort(array);
		assertArrayEquals(array, sortArray);
	}

	/**
	 * 测试冒泡排序
	 */
	@RepeatedTest(3)
	void testBubbleSort() {
		BubbleSort.bubbleSort(sortArray);
	}

	/**
	 * 测试插入排序
	 */
	@RepeatedTest(3)
	public void testInseretionSort() {
		InseretionSort.insertSort(sortArray, 0, sortArray.length - 1);
	}

	/**
	 * 测试快速排序
	 */
	@RepeatedTest(3)
	public void testQuickSort() {
		QuickSort.quickSort(sortArray, 0, sortArray.length - 1);
	}

	/**
	 * 测试改进的快速排序
	 */
	@RepeatedTest(3)
	public void testQuickSortImprove() {
		QuickSortImprove.quickSort(sortArray, 0, sortArray.length - 1);
	}

	/**
	 * 测试选择排序
	 */
	@RepeatedTest(3)
	public void testSelectionSort() {
		SelectionSort.selectionSort(sortArray);
	}

}
