package com.sweetmanor.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 冒泡排序示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class BubbleSort {

    /**
     * 冒泡排序方法
     */
    public static void bubbleSort(int[] array) {
        int last = array.length - 1;
        // 外层控制循环次数，内层控制比较的元素个数。采用浮起策略，每次把最小的数浮起
        for (int i = 0; i < last; i++)
            for (int j = last; j > i; j--)
                if (array[j] < array[j - 1]) // 如果后面的元素小，则交换位置
                    ArrayUtils.swap(array, j, j - 1);
    }

}
