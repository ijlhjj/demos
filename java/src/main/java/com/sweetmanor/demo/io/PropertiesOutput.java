package com.sweetmanor.demo.io;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Properties配置文件示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class PropertiesOutput {

    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("user", "yeeku");
        prop.setProperty("password", "123");
        prop.store(new FileOutputStream(File.createTempFile("user-", ".ini")), null);
    }

}
