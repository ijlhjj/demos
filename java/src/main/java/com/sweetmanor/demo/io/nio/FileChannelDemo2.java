package com.sweetmanor.demo.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;

/**
 * FileChannel实现文件内容的追加 <br />
 * 示例中读写使用了同一个文件通道，效果是使文件内容输出2次。此处使用临时文件只能输出一个空文件。
 *
 * @author wenz
 * @version 1.0 2016-06-17
 */
public class FileChannelDemo2 {

    public static void main(String[] args) throws IOException {
        File file = File.createTempFile("out-", ".log");
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");// 以读写方式随机存取
        ) {
            FileChannel randomChannel = raf.getChannel();
            ByteBuffer buffer = randomChannel.map(READ_ONLY, 0, file.length());
            randomChannel.position(file.length());// 移动记录指针到文件最后
            randomChannel.write(buffer);// 将buffer数据写入Channel
        }
    }

}
