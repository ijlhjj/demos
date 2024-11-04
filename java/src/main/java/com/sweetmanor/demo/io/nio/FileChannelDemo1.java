package com.sweetmanor.demo.io.nio;

import com.sweetmanor.common.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;

/**
 * FileChannel实现文件的复制 <br />
 * FileChannel即可用于输入，也可用于输出，但使用方式受所包装节点流的限制
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FileChannelDemo1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        File inFile = new File(Const.classPath + "log4j2.xml");
        File outFile = File.createTempFile("log4j2-", ".xml");

        try (FileInputStream fis = new FileInputStream(inFile); // 文件输入流
             FileOutputStream fos = new FileOutputStream(outFile);// 文件输出流
        ) {
            FileChannel inChannel = fis.getChannel(); // 获取文件输入流通道
            FileChannel outChannel = fos.getChannel();// 获取文件输出流通道

            MappedByteBuffer buffer = inChannel.map(READ_ONLY, 0, inFile.length());// 把输入通道中的数据映射成ByteBuffer
            outChannel.write(buffer);// 把ByteBuffer中的数据写入输出通道，实现文件的复制
            buffer.clear();// 清空缓冲区

            // 解码后输出buffer中的内容
            Charset charset = StandardCharsets.UTF_8;
            CharBuffer charBuffer = charset.decode(buffer);
            logger.info(charBuffer);
        }
    }

}
