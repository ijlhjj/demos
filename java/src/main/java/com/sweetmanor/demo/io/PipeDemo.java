package com.sweetmanor.demo.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 输入输出管道示例
 *
 * @author wenz
 * @version 1.0 2016-06-15
 */
public class PipeDemo {
    private static PipedInputStream pipedIS = new PipedInputStream();
    private static PipedOutputStream pipedOS = new PipedOutputStream();

    public static void main(String[] args) throws IOException {
        pipedIS.connect(pipedOS); // 连接两个管道
        new Thread(PipeDemo::pipedWriter).start(); // 启动单独线程执行管道写入操作

        final int BUFFER_SIZE = 1024;
        byte[] inArray = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        do {// 启动读取管道，循环读入
            bytesRead = pipedIS.read(inArray, 0, BUFFER_SIZE);
            System.out.println("读入" + bytesRead + "字节");
        } while (bytesRead != -1);
    }

    /**
     * 管道写入方法
     */
    private static void pipedWriter() {
        final int BUFFER_SIZE = 2048;
        byte[] outArray = new byte[BUFFER_SIZE];
        while (true) {
            try {// 因为Runnable接口方法不能抛出异常，所以此处必须进行异常捕获
                pipedOS.write(outArray, 0, BUFFER_SIZE);
            } catch (IOException e) {
                System.err.println("写操作错误");
                System.exit(1);
            }
            System.out.println("发送" + BUFFER_SIZE + "字节...");
        }
    }

}
