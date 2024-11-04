package com.sweetmanor.algorithm.genetic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 遗传算法 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2024-01-06
 */
public class GeneticAlgorithm<C extends Chromosome<C>> {
    private static final Logger logger = LogManager.getLogger();

    private List<C> population; //种群
    private final double mutationChance; //突变概率
    private final double crossoverChance; //交换概率
    private final SelectionType selectionType; //选择方法
    private final Random random;

    public GeneticAlgorithm(List<C> initialPopulation, double mutationChance, double crossoverChance, SelectionType selectionType) {
        this.population = new ArrayList<>(initialPopulation);
        this.mutationChance = mutationChance;
        this.crossoverChance = crossoverChance;
        this.selectionType = selectionType;
        this.random = new Random();
    }

    /**
     * 轮盘式选择法
     *
     * @param wheel    每条染色体的适应度
     * @param numPicks 染色体数量
     */
    private List<C> pickRoulette(double[] wheel, int numPicks) {
        List<C> picks = new ArrayList<>();
        for (int i = 0; i < numPicks; i++) {
            double pick = random.nextDouble();
            for (int j = 0; j < wheel.length; j++) {
                pick -= wheel[j];
                if (pick <= 0) {
                    picks.add(population.get(j));
                    break;
                }
            }
        }
        return picks;
    }

    /**
     * 锦标赛选择法
     *
     * @param numParticipants 参赛者数量
     * @param numPicks        染色体数量
     */
    private List<C> pickTournament(int numParticipants, int numPicks) {
        Collections.shuffle(population); //乱序
        List<C> tournament = population.subList(0, numParticipants);
        tournament.sort(Collections.reverseOrder()); //按适应度倒排序
        return tournament.subList(0, numPicks);
    }

    /**
     * 遗传迭代
     */
    private void reproduceAndReplace() {
        List<C> nextPopulation = new ArrayList<>(); //下一代种群

        while (nextPopulation.size() < population.size()) {
            List<C> parents;
            if (selectionType == SelectionType.ROULETTE) { //轮盘式选择法
                double totalFitness = population.stream().mapToDouble(C::fitness).sum(); //适应度总和
                double[] wheel = population.stream().mapToDouble(c -> c.fitness() / totalFitness).toArray(); //每条染色体的适应度比例
                parents = pickRoulette(wheel, 2); //选择双亲
            } else { //锦标赛选择法
                parents = pickTournament(population.size() / 2, 2); //使用半数进行选择
            }

            if (random.nextDouble() < crossoverChance) { //双亲结合
                C parent1 = parents.get(0);
                C parent2 = parents.get(1);
                nextPopulation.addAll(parent1.crossover(parent2));
            } else { //直接使用双亲
                nextPopulation.addAll(parents);
            }
        }

        //奇数染色体个数时移除第 1 个
        if (nextPopulation.size() > population.size())
            nextPopulation.remove(0);

        population = nextPopulation; //更新为 下一代种群
    }

    /**
     * 染色体突变
     */
    private void mutate() {
        for (C individual : population) {
            if (random.nextDouble() < mutationChance)
                individual.mutate();
        }
    }

    /**
     * 运行遗传算法
     *
     * @param maxGenerations 最多迭代次数
     * @param threshold      适应度阈值
     */
    public C run(int maxGenerations, double threshold) {
        C best = Collections.max(population).copy(); //获取适应度最强染色体副本

        //分代进行循环
        for (int i = 0; i < maxGenerations; i++) {
            //达到阈值时返回 最佳染色体
            if (best.fitness() >= threshold)
                return best;

            //测试输出
            logger.info("Generation {} Best {} Avg {}", i, best.fitness(), population.stream().mapToDouble(C::fitness).average().orElse(0.0));

            //遗传迭代
            reproduceAndReplace();

            //染色体突变
            mutate();

            C highest = Collections.max(population);
            //找到适应度更强染色体
            if (highest.fitness() > best.fitness())
                best = highest.copy();
        }

        return best; //完成迭代次数时返回 最佳染色体
    }

    /**
     * 选择方法的类型：轮盘、锦标赛
     */
    public enum SelectionType {
        ROULETTE, TOURNAMENT;
    }

}
