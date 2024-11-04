package com.sweetmanor.demo.gui.swing;

import com.sweetmanor.common.Const;
import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * 程序预加载窗体示例
 */
public class JSplashWindowPlus extends JWindow implements Runnable {
    private static final int INTERVAL = 100;// 程序休眠间隔的毫秒数

    Thread splashThread = null;
    private JProgressBar progress;

    public JSplashWindowPlus() throws MalformedURLException {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JPanel splash = new JPanel(new BorderLayout());
        splash.add(new JLabel(new ImageIcon(Const.classPath + "images/zoom.png")), BorderLayout.CENTER);

        progress = new JProgressBar(1, 100);
        progress.setStringPainted(true); // 设置进度条启动时可显示字符串
        progress.setBorderPainted(true); // 设置进度条边框
        progress.setString("程序加载中...");
        progress.setBackground(Color.white);

        splash.add(progress, BorderLayout.SOUTH); // 将进度条加入内容窗格中
        setContentPane(splash);
        Dimension screen = getToolkit().getScreenSize();

        setSize(400, 300);

        setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2); // 设置显示位置
    }

    public void start() {
        this.toFront();
        splashThread = new Thread(this);
        splashThread.start();
    }

    @SuppressWarnings("deprecation")
    public void run() {
        show();
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(INTERVAL);
                progress.setValue(progress.getValue() + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    static void showFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(640, 480);
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Thread.sleep(INTERVAL * 101);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) throws MalformedURLException {
        JSplashWindowPlus splash = new JSplashWindowPlus();
        splash.start();
        showFrame("主窗体");
    }

}
