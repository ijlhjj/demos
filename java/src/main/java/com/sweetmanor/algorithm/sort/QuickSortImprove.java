package com.sweetmanor.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

/**
 * 改进的快速排序 <br />
 * 快速排序在数据量小的时候效率没有优势，所以设定一个值，小于此数量时使用插入排序
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class QuickSortImprove {
    private static final int CUTOFF = 20;
    private static Random random = new Random();

    public static void quickSort(int[] array, int left, int right) {
        if (left + CUTOFF <= right) {// 待排序数组大于CUTOFF个元素，使用快速排序
            int pivot = partition(array, left, right);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        } else {// 少量元素使用插入排序
            InseretionSort.insertSort(array, left, right);
        }
    }

    /**
     * 快速排序实际排序函数
     *
     * @param array 待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    private static int partition(int[] array, int left, int right) {
        int index = left + random.nextInt(right - left + 1); // 随机选取一个索引
        int pivot = array[index];

        ArrayUtils.swap(array, index, right);// 把基准元素放到数组的最右边
        for (int i = index = left; i < right; i++)// 循环判断所有元素，index存放比基准元素小的索引位置
            if (array[i] <= pivot) // 如果当前元素比基准元素小，交换到左边，索引值加1
                ArrayUtils.swap(array, index++, i);
        ArrayUtils.swap(array, index, right);// 最后把基准元素交换到正确的位置，返回索引值

        return index;
    }

}
