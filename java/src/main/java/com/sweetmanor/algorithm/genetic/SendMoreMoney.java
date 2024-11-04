package com.sweetmanor.algorithm.genetic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 数字字谜问题 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2024-01-09
 */
public class SendMoreMoney extends Chromosome<SendMoreMoney> {
    private static final Logger logger = LogManager.getLogger();

    private final List<Character> letters;
    private final Random random;

    public SendMoreMoney(List<Character> letters) {
        this.letters = letters;
        random = new Random();
    }

    /**
     * 随机染色体生成
     */
    public static SendMoreMoney randomInstance() {
        List<Character> letters = new ArrayList<>(List.of('S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y', ' ', ' '));
        Collections.shuffle(letters);
        return new SendMoreMoney(letters);
    }

    /**
     * 适应度函数
     * 计算fitness()的最大值，所以需要将其翻转（使最小值变为最大值）
     */
    @Override
    public double fitness() {
        int s = letters.indexOf('S');
        int e = letters.indexOf('E');
        int n = letters.indexOf('N');
        int d = letters.indexOf('D');
        int m = letters.indexOf('M');
        int o = letters.indexOf('O');
        int r = letters.indexOf('R');
        int y = letters.indexOf('Y');

        int send = s * 1000 + e * 100 + n * 10 + d;
        int more = m * 1000 + o * 100 + r * 10 + e;
        int money = m * 10000 + o * 1000 + n * 100 + e * 10 + y;

        int difference = Math.abs(money - (send + more));
        return 1.0 / (difference + 1.0); //加 1 避免除数为 0
    }

    @Override
    public List<SendMoreMoney> crossover(SendMoreMoney other) {
        SendMoreMoney child1 = new SendMoreMoney(new ArrayList<>(letters));
        SendMoreMoney child2 = new SendMoreMoney(new ArrayList<>(other.letters));

        int idx1 = random.nextInt(letters.size());
        int idx2 = random.nextInt(other.letters.size());
        Character l1 = letters.get(idx1);
        Character l2 = other.letters.get(idx2);

        int idx3 = letters.indexOf(l2);
        int idx4 = other.letters.indexOf(l1);

        //随机选择两个索引，然后互换字母
        Collections.swap(child1.letters, idx1, idx3);
        Collections.swap(child2.letters, idx2, idx4);

        return List.of(child1, child2);
    }

    @Override
    public void mutate() {
        int idx1 = random.nextInt(letters.size());
        int idx2 = random.nextInt(letters.size());
        //随机交换两个位置的元素
        Collections.swap(letters, idx1, idx2);
    }

    @Override
    public SendMoreMoney copy() {
        return new SendMoreMoney(new ArrayList<>(letters));
    }

    public static void main(String[] args) {
        final int POPULATION_SIZE = 1000; //种群染色体数量
        final int GENERATIONS = 1000; //最多迭代次数
        final double THRESHOLD = 1.0; //阈值

        //初始化种群
        List<SendMoreMoney> initialPopulation = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++)
            initialPopulation.add(SendMoreMoney.randomInstance());

        //创建遗传算法
        GeneticAlgorithm<SendMoreMoney> ga = new GeneticAlgorithm<>(initialPopulation, 0.2, 0.7, GeneticAlgorithm.SelectionType.ROULETTE);

        //求解并输出结果
        SendMoreMoney result = ga.run(GENERATIONS, THRESHOLD);
        logger.info(result);
    }

    @Override
    public String toString() {
        int s = letters.indexOf('S');
        int e = letters.indexOf('E');
        int n = letters.indexOf('N');
        int d = letters.indexOf('D');
        int m = letters.indexOf('M');
        int o = letters.indexOf('O');
        int r = letters.indexOf('R');
        int y = letters.indexOf('Y');

        int send = s * 1000 + e * 100 + n * 10 + d;
        int more = m * 1000 + o * 100 + r * 10 + e;
        int money = m * 10000 + o * 1000 + n * 100 + e * 10 + y;

        int difference = Math.abs(money - (send + more));
        return (send + " + " + more + " = " + money + " Difference: " + difference);
    }

}
