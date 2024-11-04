package com.sweetmanor.algorithm.fun;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 递归替代多层for循环示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class RecursionReplaceFor {
    private static final Logger logger = LogManager.getLogger();

    private final char[] numCodes = {'0', '1', '2', '3'};// 元素字典表
    private int index = 0; // 序号，为调试方便

    // 字典表的n位组合，在创建破解字典时非常有用
    public void replaceNFor(int n, StringBuilder prefix) {
        if (n == 1) {
            for (char numCode : numCodes)
                logger.info("{}: {}{}", (++index), prefix, numCode);
        } else {
            for (char numCode : numCodes) {
                StringBuilder temp = new StringBuilder(prefix);// 此处必须新建副本
                temp.append(numCode);
                replaceNFor(n - 1, temp);
            }
        }
    }

    public static void main(String[] args) {
        RecursionReplaceFor rrf = new RecursionReplaceFor();
        StringBuilder prefix = new StringBuilder();// 前缀
        rrf.replaceNFor(3, prefix);// 3位字典
    }

}
