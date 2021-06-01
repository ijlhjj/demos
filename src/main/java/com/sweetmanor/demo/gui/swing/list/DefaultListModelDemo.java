package com.sweetmanor.demo.gui.swing.list;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.sweetmanor.utils.FrameUtil;

/**
 * 列表模型使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class DefaultListModelDemo {
	JFrame frame = new JFrame("测试DefaultListModel");

	public void init() {
		DefaultListModel<String> bookModel = new DefaultListModel<String>();
		bookModel.addElement("疯狂Java讲义");
		bookModel.addElement("轻量级Java EE企业应用实战");
		bookModel.addElement("疯狂Android讲义");
		bookModel.addElement("疯狂Ajax讲义");
		bookModel.addElement("经典Java EE企业应用实战");

		JList<String> bookList = new JList<String>(bookModel);
		bookList.setVisibleRowCount(4);
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(new JScrollPane(bookList));

		JPanel p = new JPanel();
		JTextField bookName = new JTextField(20);
		p.add(bookName);

		JButton btAdd = new JButton("添加指定图书");
		btAdd.addActionListener((e) -> {
			if (!bookName.getText().trim().equals("")) {
				bookModel.addElement(bookName.getText());
			}
		});
		p.add(btAdd);

		JButton btDel = new JButton("删除选中图书");
		btDel.addActionListener((e) -> {
			if (bookList.getSelectedIndex() >= 0) {
				bookModel.removeElementAt(bookList.getSelectedIndex());
			}
		});
		p.add(btDel);

		frame.add(p, BorderLayout.SOUTH);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new DefaultListModelDemo().init();
	}

}
