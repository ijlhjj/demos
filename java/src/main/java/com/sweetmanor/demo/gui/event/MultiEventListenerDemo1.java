package com.sweetmanor.demo.gui.event;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 一个组件注册多个事件处理器示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class MultiEventListenerDemo1 {
    JFrame frame = new JFrame("多事件处理器示例");
    JTextArea ta = new JTextArea(6, 40);

    public void init() {
        frame.add(ta);

        FirstListener firstListener = new FirstListener();
        SecondListener secondListener = new SecondListener();

        Panel btPanel = new Panel();
        frame.add(btPanel, BorderLayout.SOUTH);

        JButton bt1 = new JButton("按钮一");
        btPanel.add(bt1);
        bt1.addActionListener(firstListener); // 给第一个按钮注册两个监听器
        bt1.addActionListener(secondListener);

        JButton bt2 = new JButton("按钮二");
        btPanel.add(bt2);
        bt2.addActionListener(firstListener);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MultiEventListenerDemo1().init();
    }

    class FirstListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ta.append("第一个事件监听器被触发，事件源是：" + e.getActionCommand() + "\n");
        }
    }

    class SecondListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ta.append("第二个事件监听器被触发，单击了：" + e.getActionCommand() + "\n");
        }
    }

}
