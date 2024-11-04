package com.sweetmanor.demo.gui.swing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

/**
 * 当前可用的PLAF：Swing组件显示风格，相当于主题
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class AllLookAndFeel {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("当前可用的LAF：");
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            logger.info("{} ---> {}", info.getName(), info);
    }

}
