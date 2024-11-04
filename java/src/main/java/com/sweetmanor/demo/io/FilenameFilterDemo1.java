package com.sweetmanor.demo.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 自定义文件名过滤器示例
 *
 * @author wenz
 * @version 1.0 2016-06-14
 */
public class FilenameFilterDemo1 {

    public static void main(String[] args) {
        File file = new File(".");
        String[] fileList = file.list(new MyFilenameFilter());// 以自定义过滤器列出当前目录符合要求的文件路径
        if (fileList != null) {
            for (String filePath : fileList) {
                System.out.println(filePath);
            }
        }
    }

}

class MyFilenameFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return name.endsWith("java") || new File(name).isDirectory();// 包含所有目录和以java结尾的文件
    }
}
