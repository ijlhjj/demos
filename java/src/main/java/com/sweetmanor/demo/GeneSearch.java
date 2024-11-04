package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * 基因序列搜索 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-11
 */
public class GeneSearch {
    private static final Logger logger = LogManager.getLogger();

    private final ArrayList<Codon> codons = new ArrayList<>(); //密码子列表

    public GeneSearch(String geneStr) {
        for (int i = 0; i < geneStr.length() - 3; i += 3)
            codons.add(new Codon(geneStr.substring(i, i + 3)));
    }

    /**
     * 线性查找
     */
    public boolean linearContains(Codon key) {
        for (Codon codon : codons) {
            if (codon.compareTo(key) == 0)
                return true;
        }
        return false;
    }

    /**
     * 二分查找
     */
    public boolean binaryContains(Codon key) {
        ArrayList<Codon> sortedCodons = new ArrayList<>(codons);
        Collections.sort(sortedCodons);

        int low = 0;
        int high = sortedCodons.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int c = codons.get(mid).compareTo(key);

            if (c < 0)
                low = mid + 1;
            else if (c > 0)
                high = mid - 1;
            else
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        String geneStr = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT";
        GeneSearch myGene = new GeneSearch(geneStr);

        Codon acg = new Codon("ACG");
        Codon gat = new Codon("GAT");

        logger.info(myGene.linearContains(acg)); // true
        logger.info(myGene.linearContains(gat)); // false

        logger.info(myGene.binaryContains(acg)); // true
        logger.info(myGene.binaryContains(gat)); // false
    }

    /**
     * 核苷酸
     */
    public enum Nucleotide {
        A, C, G, T
    }

    /**
     * 密码子
     */
    public static class Codon implements Comparable<Codon> {
        public final Nucleotide first;
        public final Nucleotide second;
        public final Nucleotide third;

        //使用 Comparator 实现 compareTo 方法
        private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first).thenComparing((Codon c) -> c.second).thenComparing((Codon c) -> c.third);

        public Codon(String codonStr) {
            first = Nucleotide.valueOf(codonStr.substring(0, 1));
            second = Nucleotide.valueOf(codonStr.substring(1, 2));
            third = Nucleotide.valueOf(codonStr.substring(2, 3));
        }

        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this, other);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Codon codon = (Codon) o;
            return first == codon.first && second == codon.second && third == codon.third;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }
    }

}
