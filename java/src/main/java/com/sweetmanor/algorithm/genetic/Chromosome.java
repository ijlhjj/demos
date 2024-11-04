package com.sweetmanor.algorithm.genetic;

import java.util.List;

/**
 * 染色体 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2024-01-06
 */
public abstract class Chromosome<T extends Chromosome<T>> implements Comparable<T> {

    // 适应度函数
    public abstract double fitness();

    // 交换
    public abstract List<T> crossover(T other);

    // 突变
    public abstract void mutate();

    public abstract T copy();

    /**
     * 使用适应度进行比较
     */
    @Override
    public int compareTo(T other) {
        Double mine = this.fitness();
        Double theirs = other.fitness();
        return mine.compareTo(theirs);
    }

}
