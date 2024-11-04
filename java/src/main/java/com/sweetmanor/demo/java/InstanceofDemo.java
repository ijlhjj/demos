package com.sweetmanor.demo.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * instanceof 运算符示例
 *
 * @author ijlhjj
 * @version 1.0 2014-11-05
 */
public class InstanceofDemo {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Object obj = "Hello";
        logger.info(obj instanceof Object);
        logger.info(obj instanceof String);
        logger.info(obj instanceof Comparable);
        logger.info(obj instanceof Math);

        String str = "Hello";
        // String既不是Math类也不是其父类，编译无法通过
        // System.out.println(str instanceof Math);
    }

}
