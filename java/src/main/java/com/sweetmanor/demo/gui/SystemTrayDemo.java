package com.sweetmanor.demo.gui;

import com.sweetmanor.common.Const;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * 系统托盘示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class SystemTrayDemo {

    public void init() {
        if (SystemTray.isSupported()) {
            try {
                PopupMenu popMenu = new PopupMenu();// 弹出菜单

                MenuItem exitMenu = new MenuItem("Exit");
                exitMenu.addActionListener(event -> System.exit(0));

                popMenu.add(exitMenu);

                Image icon = ImageIO.read(new File(Const.classPath, "images/qq.png"));
                final TrayIcon trayIcon = new TrayIcon(icon, "My QQ", popMenu);// 创建系统托盘图标
                trayIcon.setImageAutoSize(true);// 自动调整图标大小
                SystemTray.getSystemTray().add(trayIcon);// 添加到系统托盘区域
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SystemTrayDemo().init();
    }

}
