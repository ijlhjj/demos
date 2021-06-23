package com.sweetmanor.demo.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.sweetmanor.common.Const;

/**
 * FileChannel读取文件内容的标准流程 <br />
 * 采用按块读取方式防止文件过大造成内存溢出
 * 
 * @version 1.0 2016-06-17
 * @author wenz
 */
public class FileChannelDemo3_ReadFile {

	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream(Const.classPath + "log4j2.xml"); // 创建文件输入节点流
				FileChannel channel = fis.getChannel();) {
			ByteBuffer bBuffer = ByteBuffer.allocate(1024);// 创建一个1024字节的缓冲区
			// 循环读取文件内容
			while (channel.read(bBuffer) != -1) {
				bBuffer.flip();// 翻转缓冲区，在准备从缓冲区中读取数据时调用flip方法
				Charset charset = Charset.forName("UTF-8");
				CharBuffer cBuffer = charset.decode(bBuffer);// 读取数据并解码
				System.out.println(cBuffer);
				bBuffer.clear();// 清空缓冲区，在重新写缓冲区时调用clear方法
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
