package com.sweetmanor.demo.gui.awt.component;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.sweetmanor.utils.FrameUtil;

/**
 * 对话框示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class DialogDemo1 {
	private JFrame frame = new JFrame("对话框示例");

	public void init() {
		final Dialog modal = new Dialog(frame, "模式对话框", true);// 第三个参数控制模式、非模式
		modal.setBounds(20, 30, 400, 300);
		modal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				modal.dispose();
			}
		});

		final Dialog notModal = new Dialog(frame, "非模式对话框", false);
		notModal.setBounds(100, 200, 400, 300);
		notModal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				notModal.dispose();
			}
		});

		Button btModal = new Button("打开模式对话框");
		btModal.addActionListener(event -> modal.setVisible(true));
		frame.add(btModal);

		Button btNotModal = new Button("打开非模式对话框");
		btNotModal.addActionListener(event -> notModal.setVisible(true));
		frame.add(btNotModal, BorderLayout.SOUTH);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new DialogDemo1().init();
	}

}
