package com.sweetmanor.demo.gui.layout;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * BoxLayout示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class BoxSpaceDemo1 {
    JFrame frame = new JFrame("BoxLayout示例");

    public void init() {
        Box horizontal = Box.createHorizontalBox(); // 水平方向的盒型布局
        frame.add(horizontal, BorderLayout.NORTH);
        horizontal.add(new JButton("水平按钮一"));
        horizontal.add(Box.createHorizontalGlue()); // 水平间距，可在两个方向上扩充
        horizontal.add(new JButton("水平按钮二"));
        horizontal.add(Box.createHorizontalStrut(10)); // 水平间距，宽度固定，只在垂直方向扩充
        horizontal.add(new JButton("水平按钮三"));

        Box vertical = Box.createVerticalBox(); // 垂直方向的盒型布局
        frame.add(vertical);
        vertical.add(new JButton("垂直按钮一"));
        vertical.add(Box.createVerticalGlue()); // 垂直间距，可在两个方向扩充
        vertical.add(new JButton("垂直按钮二"));
        vertical.add(Box.createVerticalStrut(10)); // 垂直间距，高度固定，只在水平方向扩充
        vertical.add(new JButton("垂直按钮三"));

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BoxSpaceDemo1().init();
    }

}
