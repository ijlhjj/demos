package com.sweetmanor.demo.gui.awt.container;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * ScrollPane示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class ScrollPaneDemo1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ScrollPane示例");

        // 指定ScrollPane容器总是显示滚动条，ScrollPane的布局管理器默认为null
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        System.out.println(scrollPane.getLayout());

        // 以下添加的两个控件会重叠，所以只显示后添加的控件
        scrollPane.add(new TextField(10));
        scrollPane.add(new Button("单击我"));
        frame.add(scrollPane);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
