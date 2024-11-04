package com.sweetmanor.demo.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * GregorianCalendar类判断闰年的方法
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class GregorianCalendarDemo {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        logger.info("1900-2100年之间的闰年：");
        for (int i = 1900; i < 2100; i++)
            if (((GregorianCalendar) cal).isLeapYear(i))
                logger.info(i);
    }

}
