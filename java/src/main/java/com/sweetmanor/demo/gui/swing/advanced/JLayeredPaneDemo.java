package com.sweetmanor.demo.gui.swing.advanced;

import com.sweetmanor.common.Const;
import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * 分层面板示例：编号大的层在上面
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class JLayeredPaneDemo {
    JFrame frame = new JFrame("分层面板示例");

    public void init() {
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);
        layeredPane.setPreferredSize(new Dimension(500, 400));

        layeredPane.add(new ContentPanel(50, 10, "疯狂Java讲义", Const.classPath + "images/swing/java.png"), JLayeredPane.MODAL_LAYER);
        layeredPane.add(new ContentPanel(150, 60, "疯狂Android讲义", Const.classPath + "images/swing/android.png"), JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(new ContentPanel(250, 100, "轻量级Java EE企业应用实战", Const.classPath + "images/swing/ee.png"), 4);// 疑问：4不是应该在MODAL_LAYER之上吗?

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new JLayeredPaneDemo().init();
    }

    // 带图片的Panel组件
    static class ContentPanel extends JPanel {
        @Serial
        private static final long serialVersionUID = 1L;

        public ContentPanel(int xPos, int yPos, String title, String img) {
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            add(imgLabel);
            setBounds(xPos, yPos, 170, 255);
        }
    }

}
