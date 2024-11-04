package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TimeZone;

/**
 * 获取系统运行环境参数
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class SystemEnvironment {
    private static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "{} = {}";

    /**
     * 当前JVM所支持的可用字符集
     */
    public static void charsetAvailable() {
        logger.info("当前JVM所支持的可用字符集：");
        SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
        charsetsMap.values().forEach(logger::info);
    }

    /**
     * 系统所有可用字体
     */
    public static void fontAvaiable() {
        logger.info("系统所有可用字体：");
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String fontName : fontNames)
            logger.info(fontName);
    }

    /**
     * ImageIO类支持的读、写文件类型
     */
    public static void imageIOSupportFileFormat() {
        logger.info("ImageIO类支持读取的图片格式：");
        String[] readFormat = ImageIO.getReaderFileSuffixes();
        for (String format : readFormat)
            logger.info(format);

        logger.info("ImageIO类支持写入的图片格式：");
        String[] writeFormat = ImageIO.getWriterFileSuffixes();
        for (String format : writeFormat)
            logger.info(format);
    }

    /**
     * 当前JVM所支持的Locale
     */
    public static void localeAvailable() {
        logger.info("当前JVM所支持的Locale：");
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales)
            logger.info("{} = {} , {} = {}", locale.getDisplayCountry(), locale.getCountry(), locale.getDisplayLanguage(), locale.getLanguage());
    }

    /**
     * 当前系统设置的属性值
     */
    public static void systemProperties() {
        logger.info("当前系统设置的属性值：");
        System.getProperties().forEach((key, value) -> logger.info(MESSAGE, key, value));
    }

    /**
     * 所有可用时区
     */
    public static void timeZoneAvailable() {
        logger.info("所有可用时区：");
        String[] timeZoneIds = TimeZone.getAvailableIDs();
        for (String id : timeZoneIds) {
            TimeZone tz = TimeZone.getTimeZone(id);
            logger.info(MESSAGE, tz.getID(), tz.getDisplayName());
        }

        // 当前系统默认时区
        TimeZone myTimeZone = TimeZone.getDefault();
        logger.info(MESSAGE, myTimeZone.getID(), myTimeZone.getDisplayName());
    }

    public static void main(String[] args) {
        charsetAvailable();// 当前JVM所支持的可用字符集
        fontAvaiable();// 系统所有可用字体
        imageIOSupportFileFormat();// ImageIO类支持的读、写文件类型
        localeAvailable();// 当前JVM所支持的Locale
        systemProperties();// 当前系统设置的属性值
        timeZoneAvailable();// 所有可用时区
    }

}
