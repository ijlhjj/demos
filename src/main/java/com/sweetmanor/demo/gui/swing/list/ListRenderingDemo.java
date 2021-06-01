package com.sweetmanor.demo.gui.swing.list;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.sweetmanor.common.Const;
import com.sweetmanor.utils.FrameUtil;

/**
 * 自定义列表的渲染示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ListRenderingDemo {
	JFrame frame = new JFrame("好友列表");

	public void init() {
		String[] friends = new String[] { "李清照", "苏格拉底", "李白", "弄玉", "虎头" };
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
	private static final long serialVersionUID = 1L;

	ImageIcon icon;
	String name;
	Color background;
	Color foreground;

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {
		icon = new ImageIcon(Const.classPath + "images/swing/" + value + ".gif");
		name = value;
		background = isSelected ? list.getSelectionBackground() : list.getBackground();
		foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
		return this;
	}

	public void paintComponent(Graphics g) {
		int imageWidth = icon.getImage().getWidth(null);
		int imageHeight = icon.getImage().getHeight(null);
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foreground);
		g.drawImage(icon.getImage(), getWidth() / 2 - imageWidth / 2, 10, null);
		g.setFont(new Font("sansSerif", Font.BOLD, 18));
		g.drawString(name, getWidth() / 2 - name.length() * 10, imageHeight + 30);
	}

	public Dimension getPreferredSize() {
		return new Dimension(60, 80);
	}

}
