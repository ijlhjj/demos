package com.sweetmanor.demo.gui.awt.component;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 文件对话框示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class FileDialogDemo1 {
    private final JFrame frame = new JFrame("文件对话框示例");

    public void init() {
        final FileDialog openFileDialog = new FileDialog(frame, "打开文件", FileDialog.LOAD);// 打开文件对话框
        Button btOpen = new Button("打开文件");
        btOpen.addActionListener(event -> {
            openFileDialog.setVisible(true);
            System.out.println(openFileDialog.getDirectory() + openFileDialog.getFile());
        });
        frame.add(btOpen);

        final FileDialog saveFileDialog = new FileDialog(frame, "保存文件", FileDialog.SAVE);// 保存文件对话框
        Button btSave = new Button("保存文件");
        btSave.addActionListener(event -> {
            saveFileDialog.setVisible(true);
            System.out.println(saveFileDialog.getDirectory() + saveFileDialog.getFile());
        });
        frame.add(btSave, BorderLayout.SOUTH);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FileDialogDemo1().init();
    }

}
