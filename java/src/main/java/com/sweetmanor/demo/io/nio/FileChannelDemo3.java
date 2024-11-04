package com.sweetmanor.demo.io.nio;

import com.sweetmanor.common.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * FileChannel读取文件内容的标准流程 <br />
 * 采用按块读取方式防止文件过大造成内存溢出
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FileChannelDemo3 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml"); // 创建文件输入节点流
             FileChannel channel = fis.getChannel();) {
            ByteBuffer bBuffer = ByteBuffer.allocate(1024);// 创建一个1024字节的缓冲区
            // 循环读取文件内容
            while (channel.read(bBuffer) != -1) {
                bBuffer.flip();// 翻转缓冲区，在准备从缓冲区中读取数据时调用flip方法
                Charset charset = StandardCharsets.UTF_8;
                CharBuffer cBuffer = charset.decode(bBuffer);// 读取数据并解码
                logger.info(cBuffer);
                bBuffer.clear();// 清空缓冲区，在重新写缓冲区时调用clear方法
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
