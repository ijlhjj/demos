package com.sweetmanor.demo.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList方法示例 <br />
 * <p>
 * 返回内容可变但大小不可变集合。也就是说，可以在这个列表上调用set方法，但不能调用add或remove方法 <br />
 * Java9新引入的List.of方法返回内容和大小均不可修改的集合
 *
 * @author ijlhjj
 */
public class FixedSizeList {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        List<String> fixedList = Arrays.asList("疯狂Java讲义", "疯狂Java EE企业应用实战");
        logger.info(fixedList.getClass());// 返回Arrays的内部类ArrayList，与java.util.ArrayList不同

        for (String s : fixedList)
            logger.info(s);

        /* 调用以下方法会报 UnsupportedOperationException */
        // fixedList.add("疯狂android讲义");
        // fixedList.remove("疯狂Java讲义");
    }

}
