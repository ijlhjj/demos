package com.sweetmanor.demo.gui.event;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 窗体事件监听器示例。实际应用中应该优先使用窗体事件适配器 WindowAdapter
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class WindowListenerDemo1 {
    JFrame frame = new JFrame("窗体事件监听器示例");
    TextArea ta = new TextArea(6, 20);

    public void init() {
        frame.add(ta);

        frame.addWindowListener(new MyWindowListener());

        frame.pack();
        FrameUtil.center(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new WindowListenerDemo1().init();
    }

    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
            ta.append("窗口初次被打开\n");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            ta.append("用户试图关闭窗体\n");
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            ta.append("窗口被成功关闭\n");
        }

        @Override
        public void windowIconified(WindowEvent e) {
            ta.append("窗口被最小化\n");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            ta.append("窗口被恢复\n");
        }

        @Override
        public void windowActivated(WindowEvent e) {
            ta.append("窗口被激活\n");
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            ta.append("窗口失去焦点\n");
        }
    }

}
