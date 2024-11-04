package com.sweetmanor.algorithm.search;

import com.sweetmanor.common.RandomInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {
    
    private int[] array;
    private int index;
    private int key;

    @BeforeEach
    void setUp() throws Exception {
        array = RandomInt.getLittleArray();
        Arrays.sort(array);
        Random random = new Random();
        index = random.nextInt(array.length);// 随机选取一个查找值
        key = array[index];
    }

    @Test
    void testRankInAll() {
        int actual = BinarySearch.rank(array, key);
        assertEquals(index, actual);
    }

    @Test
    void testRankInScope() {
        int actual = BinarySearch.rank(array, key, 0, array.length - 1);
        assertEquals(index, actual);
    }

}
