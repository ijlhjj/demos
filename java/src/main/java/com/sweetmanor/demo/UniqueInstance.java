package com.sweetmanor.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/**
 * 采用文件独占锁方式，限制只能运行一个程序实例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class UniqueInstance {
    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        // 获取文件独占锁
        FileLock lck = new FileOutputStream(System.getProperty("java.io.tmpdir") + "lock.lck").getChannel().tryLock();
        // 如果加锁不成功，则异常退出程序
        if (lck == null) {
            logger.info("已有一个程序实例在运行，本程序将自动退出！");
            System.exit(1);
        }

        // 下面执行程序的正常步骤
        logger.info("程序已启动...");

        Thread.sleep(10000);// 为测试让本线程休眠10秒后退出
    }

}
