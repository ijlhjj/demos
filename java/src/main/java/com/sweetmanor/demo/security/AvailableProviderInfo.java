package com.sweetmanor.demo.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

/**
 * 当前可用Provider（安全提供者）信息
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class AvailableProviderInfo {
    private static final Logger logger = LogManager.getLogger();

    /*
     * 所有加密算法
     */
    public static void allProvider() {
        for (Provider p : Security.getProviders())
            for (Provider.Service s : p.getServices())
                logger.info(s.getAlgorithm());
    }

    /*
     * 信息摘要算法
     */
    public static void messageDigest() {
        for (Provider p : Security.getProviders()) {
            for (Provider.Service s : p.getServices())
                if (s.getType().equals("MessageDigest"))
                    logger.info(s.getAlgorithm());
        }
    }

    public static void main(String[] args) {
        for (Provider p : Security.getProviders()) {
            logger.info(p);// 打印当前提供者的信息
            for (Map.Entry<Object, Object> entry : p.entrySet())
                logger.info(entry.getKey());// 打印提供者的键值
        }
    }

}
