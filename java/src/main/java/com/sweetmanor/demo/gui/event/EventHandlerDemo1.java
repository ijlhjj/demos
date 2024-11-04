package com.sweetmanor.demo.gui.event;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 内部类事件处理器示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class EventHandlerDemo1 {
    JFrame frame = new JFrame("内部类事件处理器示例");
    TextField tf = new TextField(30);

    public void init() {
        frame.add(tf);

        JButton ok = new JButton("确定");
        frame.add(ok, BorderLayout.SOUTH);
        ok.addActionListener(new OkListener()); // 注册事件监听器

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EventHandlerDemo1().init();
    }

    // 事件监听器类
    class OkListener implements ActionListener {
        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("用户第 " + (++count) + " 次单击OK按钮");
            tf.setText("Hello World! " + (count));
        }
    }

}
