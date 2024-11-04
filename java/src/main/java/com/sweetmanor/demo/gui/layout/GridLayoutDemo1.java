package com.sweetmanor.demo.gui.layout;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * GridLayout示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class GridLayoutDemo1 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("GridLayout示例");
        frame.add(new TextField(20), BorderLayout.NORTH);

        String[] btNames = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "."};
        // GridLayout布局:
        // rows:行数3
        // cols:列数5
        // hgap:水平间距6
        // vgap:垂直间距6
        Panel panel = new Panel(new GridLayout(3, 5, 6, 6));
        for (String btName : btNames)
            panel.add(new Button(btName));
        frame.add(panel);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
