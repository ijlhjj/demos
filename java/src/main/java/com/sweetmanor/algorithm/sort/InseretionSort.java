package com.sweetmanor.algorithm.sort;

/**
 * 插入排序示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class InseretionSort {

    /**
     * 插入排序示例
     *
     * @param array 待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    public static void insertSort(int[] array, int left, int right) {
        int i;
        int j;
        int temp;
        for (i = left + 1; i <= right; i++) {
            temp = array[i]; // 缓存当前待插入元素
            j = i - 1; // 已排序数组右边界
            while (j >= left && array[j] > temp) { // 元素依次右移，找到适当插入位置
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp; // 插入当前元素
        }
    }

}
