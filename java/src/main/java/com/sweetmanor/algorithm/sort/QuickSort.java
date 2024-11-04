package com.sweetmanor.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

/**
 * 快速排序示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class QuickSort {
    private static Random random = new Random();

    /**
     * 快速排序函数
     *
     * @param array 待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    public static void quickSort(int[] array, int left, int right) {
        // 左边界小于右边界说明还有未排序元素
        if (left < right) {
            int pivot = partition(array, left, right); // 调用实际排序函数
            quickSort(array, left, pivot - 1); // 递归排序左半部分
            quickSort(array, pivot + 1, right); // 递归排序右半部分
        }
    }

    // 实际排序函数，为保证任何情况下效率都不会最差，采用随机选取
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
