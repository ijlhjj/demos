package com.sweetmanor.demo;

import java.awt.GraphicsEnvironment;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TimeZone;

import javax.imageio.ImageIO;

/**
 * 获取系统运行环境参数
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SystemEnvironment {

	/**
	 * 当前JVM所支持的可用字符集
	 */
	public static void charsetAvailable() {
		System.out.println("当前JVM所支持的可用字符集：");
		SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
		charsetsMap.values().stream().forEach(System.out::println);
	}

	/**
	 * 系统所有可用字体
	 */
	public static void fontAvaiable() {
		System.out.println("系统所有可用字体：");
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (String fontName : fontNames)
			System.out.println(fontName);
	}

	/**
	 * ImageIO类支持的读、写文件类型
	 */
	public static void imageIOSupportFileFormat() {
		System.out.println("ImageIO类支持读取的图片格式：");
		String[] readFormat = ImageIO.getReaderFileSuffixes();
		for (String format : readFormat)
			System.out.println(format);

		System.out.println("ImageIO类支持写入的图片格式：");
		String[] writeFormat = ImageIO.getWriterFileSuffixes();
		for (String format : writeFormat)
			System.out.println(format);
	}

	/**
	 * 当前JVM所支持的Locale
	 */
	public static void localeAvailable() {
		System.out.println("当前JVM所支持的Locale：");
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales)
			System.out.println(locale.getDisplayCountry() + "=" + locale.getCountry() + "  "
					+ locale.getDisplayLanguage() + "=" + locale.getLanguage());
	}

	/**
	 * 当前系统设置的属性值
	 */
	public static void systemProperties() {
		System.out.println("当前系统设置的属性值：");
		System.getProperties().forEach((key, value) -> System.out.println(key + ":\t" + value));
	}

	/**
	 * 所有可用时区
	 */
	public static void timeZoneAvailable() {
		System.out.println("所有可用时区：");
		String[] timeZoneIds = TimeZone.getAvailableIDs();
		for (String id : timeZoneIds) {
			TimeZone tz = TimeZone.getTimeZone(id);
			System.out.println(tz.getID() + " = " + tz.getDisplayName());
		}

		// 当前系统默认时区
		TimeZone myTimeZone = TimeZone.getDefault();
		System.out.println(myTimeZone.getID() + " = " + myTimeZone.getDisplayName());
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
