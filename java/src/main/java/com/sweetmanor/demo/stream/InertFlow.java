package com.sweetmanor.demo.stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 惰性流：<br />
 * <p>
 * 流是惰性的，在达到终止条件前不会处理元素，达到终止条件后只处理所需的值。
 *
 * @author wenz
 * @version 1.0 2021-04-28
 */
public class InertFlow {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        new InertFlow().run();
    }

    public void run() {
        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(this::multByTwo) // 对元素倍增
                .filter(this::divByThree) // 模3过滤
                .findFirst();
        logger.info(firstEvenDoubleDivBy3);
    }

    // 倍增方法
    public int multByTwo(int n) {
        logger.info("初始值（乘2以前）： {}", n);
        return n * 2;
    }

    // 对3取模方法
    public boolean divByThree(int n) {
        logger.info("倍增后（乘2以后，模3以前）： {}", n);
        return n % 3 == 0;
    }

}
