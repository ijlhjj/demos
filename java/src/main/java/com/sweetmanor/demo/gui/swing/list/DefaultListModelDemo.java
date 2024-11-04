package com.sweetmanor.demo.gui.swing.list;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 列表模型使用示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class DefaultListModelDemo {
    JFrame frame = new JFrame("测试DefaultListModel");

    public void init() {
        DefaultListModel<String> bookModel = new DefaultListModel<>();
        bookModel.addElement("疯狂Java讲义");
        bookModel.addElement("轻量级Java EE企业应用实战");
        bookModel.addElement("疯狂Android讲义");
        bookModel.addElement("疯狂Ajax讲义");
        bookModel.addElement("经典Java EE企业应用实战");

        JList<String> bookList = new JList<>(bookModel);
        bookList.setVisibleRowCount(4);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(new JScrollPane(bookList));

        JPanel p = new JPanel();
        JTextField bookName = new JTextField(20);
        p.add(bookName);

        JButton btAdd = new JButton("添加指定图书");
        btAdd.addActionListener(e -> {
            if (!bookName.getText().trim().isEmpty()) {
                bookModel.addElement(bookName.getText());
            }
        });
        p.add(btAdd);

        JButton btDel = new JButton("删除选中图书");
        btDel.addActionListener(e -> {
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
