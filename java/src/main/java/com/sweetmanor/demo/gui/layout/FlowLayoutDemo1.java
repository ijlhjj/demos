package com.sweetmanor.demo.gui.layout;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * FlowLayout示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class FlowLayoutDemo1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("FlowLayout示例");

        // FlowLayout布局: align:对齐方式，可接受LEFT,RIGHT,CENTER
        // hgap:水平间距20
        // vgap:垂直间距5
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

        for (int i = 0; i < 10; i++) {
            // 每个组件都有一个最佳大小（即没有冗余空间，也没有内容被遮挡），所以“按钮10”的显示宽度要比其他几个按钮略宽
            frame.add(new Button("按钮" + (i + 1)));
        }

        frame.setSize(400, 300);
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
