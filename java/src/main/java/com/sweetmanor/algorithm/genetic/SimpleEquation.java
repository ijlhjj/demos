package com.sweetmanor.algorithm.genetic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 方程式 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2024-01-07
 */
public class SimpleEquation extends Chromosome<SimpleEquation> {
    private static final Logger logger = LogManager.getLogger();

    private static final int MAX_START = 100; //初始值上限
    private static final Random random = new Random();

    private int x;
    private int y;

    public SimpleEquation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 随机染色体生成
     */
    public static SimpleEquation randomInstance() {
        return new SimpleEquation(random.nextInt(MAX_START), random.nextInt(MAX_START));
    }

    /**
     * 适应度函数使用方程式即可： 6x - x^2 + 4y - y^2
     */
    @Override
    public double fitness() {
        return 6.0 * x - x * x + 4 * y - y * y;
    }

    @Override
    public List<SimpleEquation> crossover(SimpleEquation other) {
        SimpleEquation child1 = new SimpleEquation(x, other.y);
        SimpleEquation child2 = new SimpleEquation(other.x, y);
        return List.of(child1, child2);
    }

    @Override
    public void mutate() {
        if (random.nextDouble() > 0.5) { // x 突变
            if (random.nextDouble() > 0.5)
                x++;
            else
                x--;
        } else { // y 突变
            if (random.nextDouble() > 0.5)
                y++;
            else
                y--;
        }
    }

    @Override
    public SimpleEquation copy() {
        return new SimpleEquation(x, y);
    }

    public static void main(String[] args) {
        final int POPULATION_SIZE = 20; //种群染色体数量
        final int GENERATIONS = 100; //最多迭代次数
        final double THRESHOLD = 13.0; //阈值

        //初始化种群
        List<SimpleEquation> initialPopulation = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++)
            initialPopulation.add(SimpleEquation.randomInstance());

        //创建遗传算法
        GeneticAlgorithm<SimpleEquation> ga = new GeneticAlgorithm<>(initialPopulation, 0.1, 0.7, GeneticAlgorithm.SelectionType.TOURNAMENT);

        //求解并输出结果
        SimpleEquation result = ga.run(GENERATIONS, THRESHOLD);
        logger.info(result);
    }

    @Override
    public String toString() {
        return "X=" + x + ", Y=" + y + ", Fitness=" + fitness();
    }

}
