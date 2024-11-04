package com.sweetmanor.algorithm.number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 小球反弹 <br />
 * 一小球从100米高度落下，每次落地后反弹回原高度的一半。求它在第10次落地时，共经过多少米？第10次反弹多高？
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class ReboundHeight {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        double height = 100;
        double distance = 0;

        for (int i = 0; i < 10; i++) {
            distance += 2 * height;
            height = height / 2;
        }

        distance -= 100;

        logger.info("总共经过路程：\t\t{}", distance);
        logger.info("第10次反弹高度：\t{}", height);
    }

}
