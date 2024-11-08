package com.sweetmanor.algorithm.genetic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

/**
 * 优化列表压缩 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2024-01-09
 */
public class ListCompression extends Chromosome<ListCompression> {
    private static final Logger logger = LogManager.getLogger();

    private static final List<String> ORIGINAL_LIST = List.of("Michael", "Sarah", "Joshua", "Narine", "David", "Sajid", "Melanie", "Daniel", "Wei", "Dean", "Brian", "Murat", "Lisa");
    private final List<String> myList;
    private final Random random;

    public ListCompression(List<String> list) {
        myList = new ArrayList<>(list);
        random = new Random();
    }

    public static ListCompression randomInstance() {
        ArrayList<String> tempList = new ArrayList<>(ORIGINAL_LIST);
        Collections.shuffle(tempList);
        return new ListCompression(tempList);
    }

    private int bytesCompressed() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gos = new GZIPOutputStream(baos);
            ObjectOutputStream oos = new ObjectOutputStream(gos);
            oos.writeObject(myList);
            oos.close();
            return baos.size();
        } catch (IOException ioe) {
            logger.error("Could not compress list!");
            logger.error(ioe);
            return 0;
        }
    }

    @Override
    public double fitness() {
        return 1.0 / bytesCompressed();
    }

    @Override
    public List<ListCompression> crossover(ListCompression other) {
        ListCompression child1 = new ListCompression(new ArrayList<>(myList));
        ListCompression child2 = new ListCompression(new ArrayList<>(myList));

        int idx1 = random.nextInt(myList.size());
        int idx2 = random.nextInt(other.myList.size());
        String s1 = myList.get(idx1);
        String s2 = other.myList.get(idx2);

        int idx3 = myList.indexOf(s2);
        int idx4 = other.myList.indexOf(s1);
        Collections.swap(child1.myList, idx1, idx3);
        Collections.swap(child2.myList, idx2, idx4);

        return List.of(child1, child2);
    }

    @Override
    public void mutate() {
        int idx1 = random.nextInt(myList.size());
        int idx2 = random.nextInt(myList.size());
        Collections.swap(myList, idx1, idx2);
    }

    @Override
    public ListCompression copy() {
        return new ListCompression(new ArrayList<>(myList));
    }

    public static void main(String[] args) {
        final int POPULATION_SIZE = 100;
        final int GENERATIONS = 100;
        final double THRESHOLD = 1.0;

        ListCompression originalOrder = new ListCompression(ORIGINAL_LIST);
        logger.info(originalOrder);

        ArrayList<ListCompression> initialPopulation = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++)
            initialPopulation.add(ListCompression.randomInstance());

        GeneticAlgorithm<ListCompression> ga = new GeneticAlgorithm<>(initialPopulation, 0.2, 0.7, GeneticAlgorithm.SelectionType.TOURNAMENT);

        ListCompression result = ga.run(GENERATIONS, THRESHOLD);
        logger.info(result);
    }

    @Override
    public String toString() {
        return "Order: " + myList + " Bytes: " + bytesCompressed();
    }

}
