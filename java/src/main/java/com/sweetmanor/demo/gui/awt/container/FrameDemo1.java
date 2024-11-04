package com.sweetmanor.demo.gui.awt.container;

import java.awt.*;

/**
 * Frame示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class FrameDemo1 {

    public static void main(String[] args) {
        Frame frame = new Frame("Frame示例"); // Frame默认采用BorderLayout布局
        System.out.println(frame.getLayout());

        Panel panel = new Panel(); // Panel默认采用FlowLayout布局
        System.out.println(panel.getLayout());

        panel.add(new TextField(10));
        panel.add(new Button("单击我"));
        frame.add(panel);

        frame.setBounds(400, 200, 400, 200);
        frame.setVisible(true); // Frame默认可见性为false
    }

}
