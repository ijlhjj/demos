package com.sweetmanor.algorithm.sort;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 选择排序示例
 *
 * @author ijlhjj
 * @version 1.0 2014-11-30
 */
public class SelectionSort {

    /**
     * 选择排序方法
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++)// 每次找到最小的数进行排序
                if (array[j] < array[index])
                    index = j;
            ArrayUtils.swap(array, i, index);
        }
    }

}
