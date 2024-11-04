package com.sweetmanor.algorithm.search;

/**
 * 二分查找示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class BinarySearch {

    /**
     * 二分查找：数组必须已有序-循环实现
     *
     * @param key   查找的key值
     * @param array 待查找数组（查找范围）
     * @return 元素在数组中的索引值，不存在返回-1
     */
    public static int rank(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key < array[mid])
                high = mid - 1;
            else if (key > array[mid])
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    /**
     * 二分查找：数组必须已有序-递归实现
     *
     * @param key   查找的key值
     * @param array 待查找数组
     * @param low   查找索引范围下限
     * @param high  查找索引范围上限
     * @return 元素在数组指定范围内的索引值，不存在返回-1
     */
    public static int rank(int[] array, int key, int low, int high) {
        // 如果key存在于a[]中，则 low<=index<=high
        if (low > high)
            return -1;

        int mid = (low + high) / 2;
        if (key < array[mid])
            return rank(array, key, low, mid - 1);
        else if (key > array[mid])
            return rank(array, key, mid + 1, high);
        else
            return mid;
    }

}
