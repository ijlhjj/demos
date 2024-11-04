package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.BitSet;

/**
 * 基因序列压缩 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-09
 */
public class GeneCompressed {
    private static final Logger logger = LogManager.getLogger();

    private BitSet bitSet;
    private int length; //存储字符串长度

    public GeneCompressed(String gene) {
        compress(gene);
    }

    /**
     * 压缩方法：将字符串转换后存储到 bitSet
     */
    private void compress(String gene) {
        length = gene.length(); //字符串长度
        bitSet = new BitSet(length * 2);//2位存储一个字符

        String upperGene = gene.toUpperCase();
        for (int i = 0; i < length; i++) {
            int first = 2 * i;
            int second = first + 1;
            switch (upperGene.charAt(i)) {
                case 'A': //00
                    bitSet.set(first, false);
                    bitSet.set(second, false);
                    break;
                case 'C': //01
                    bitSet.set(first, false);
                    bitSet.set(second, true);
                    break;
                case 'G': //10
                    bitSet.set(first, true);
                    bitSet.set(second, false);
                    break;
                case 'T': //11
                    bitSet.set(first, true);
                    bitSet.set(second, true);
                    break;
                default:
                    throw new IllegalArgumentException("The provided gene String contains characters other than ACGT");
            }
        }
    }

    /**
     * 解压缩方法
     */
    public String decompress() {
        if (bitSet == null)
            return "";

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length * 2; i += 2) {
            int firstBit = bitSet.get(i) ? 1 : 0;
            int secondBit = bitSet.get(i + 1) ? 1 : 0;
            int bits = firstBit << 1 | secondBit; //组合两个数位

            switch (bits) {
                case 0b00: // 00 - A
                    sb.append('A');
                    break;
                case 0b01: // 01 - C
                    sb.append('C');
                    break;
                case 0b10: // 10 - G
                    sb.append('G');
                    break;
                case 0b11: // 11 - T
                    sb.append('T');
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final String original = "TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA";
        GeneCompressed compressed = new GeneCompressed(original);
        final String decompressed = compressed.decompress();
        logger.info(decompressed);
        logger.info("original is the same as decompressed:{} ", original.equalsIgnoreCase(decompressed));
    }

}
