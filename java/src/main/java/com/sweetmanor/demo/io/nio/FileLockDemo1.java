package com.sweetmanor.demo.io.nio;

import com.sweetmanor.common.Const;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁示例
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FileLockDemo1 {

    public static void main(String[] args) throws Exception {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml"); FileChannel channel = fis.getChannel();) {
            // 获取文件锁：非阻塞、全文件、排他锁会报错，共享锁不报错
            FileLock lock = channel.tryLock(0, Long.MAX_VALUE, true);
            Thread.sleep(30000);
            lock.release();// 释放文件锁
        }
    }

}
