package com.sweetmanor.demo.java;

import com.sweetmanor.common.Const;

import java.awt.*;
import java.io.File;

/**
 * Desktop类示例 <br />
 * 调用系统关联程序打开指定文件，异步调取，不等待返回
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class DesktopDemo {

    public static void main(String[] args) throws Exception {
        Desktop desk = Desktop.getDesktop();
        desk.open(new File("D:\\"));// 调用资源管理器打开D盘
        desk.open(new File(Const.classPath, "images/zoom.png"));// 打开图片
    }

}
