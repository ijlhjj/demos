package com.sweetmanor.demo.gui.layout;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * BorderLayout示例：演示组件单向扩展的使用
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class BorderLayoutDemo3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("BorderLayout示例");

        Box box = Box.createVerticalBox(); // 垂直Box内部的组件默认会居中显示
        frame.add(box);
        box.setMinimumSize(new Dimension(200, 200));// 最小大小设置无效
        box.setMaximumSize(new Dimension(300, 600));

        Button bt = new Button("按钮");
        box.add(bt);
        bt.setMinimumSize(new Dimension(200, 200)); // 最小大小设置无效
        bt.setMaximumSize(new Dimension(300, 600));

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
