package com.sweetmanor.demo.gui.swing.list;

import com.sweetmanor.common.Const;
import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * 自定义列表的渲染示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class ListRenderingDemo {
    JFrame frame = new JFrame("好友列表");

    public void init() {
        String[] friends = new String[]{"李清照", "苏格拉底", "李白", "弄玉", "虎头"};
        JList<String> friendsList = new JList<>(friends);
        friendsList.setCellRenderer(new ImageCellRenderer());
        frame.add(new JScrollPane(friendsList));

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ListRenderingDemo().init();
    }

}

class ImageCellRenderer extends JPanel implements ListCellRenderer<String> {
    @Serial
    private static final long serialVersionUID = 1L;

    ImageIcon icon;
    String name;
    Color backgroundColor;
    Color foregroundColor;

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        icon = new ImageIcon(Const.classPath + "images/swing/" + value + ".gif");
        name = value;
        backgroundColor = isSelected ? list.getSelectionBackground() : list.getBackground();
        foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
        return this;
    }

    @Override
    public void paintComponent(Graphics g) {
        int imageWidth = icon.getImage().getWidth(null);
        int imageHeight = icon.getImage().getHeight(null);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(foregroundColor);
        g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2, 10, null);
        g.setFont(new Font("sansSerif", Font.BOLD, 18));
        g.drawString(name, getWidth() / 2 - name.length() * 10, imageHeight + 30);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 80);
    }

}
