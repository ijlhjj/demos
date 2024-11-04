package com.sweetmanor.demo.gui.awt.clipboard;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 系统剪贴板使用示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class ClipboardDemo1 {
    private final JFrame frame = new JFrame("简单的剪贴板程序");

    public void init() {
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // 获取系统剪贴板
        // final Clipboard clipboard = new Clipboard("cb"); // 创建本地剪贴板

        Box center = Box.createHorizontalBox();
        frame.add(center);

        final TextArea taSource = new TextArea(5, 20);
        taSource.append("平静由内而生。\n不要从外追寻。");
        center.add(taSource);
        final TextArea taTarget = new TextArea(5, 20);
        center.add(taTarget);

        Panel btPanel = new Panel();
        frame.add(btPanel, BorderLayout.SOUTH);

        // 复制动作，使用lambda方式
        Button btCopy = new Button("复制");
        btCopy.addActionListener(event -> {
            StringSelection contents = new StringSelection(taSource.getText());
            clipboard.setContents(contents, null);// 设置剪贴板的当前内容，剪贴板的所有者为null
        });
        btPanel.add(btCopy);

        // 粘贴动作，使用匿名内部类
        Button btPaste = new Button("粘贴");
        btPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    try {
                        String content = (String) clipboard.getData(DataFlavor.stringFlavor);// 获取剪贴板的字符串内容
                        taTarget.append(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btPanel.add(btPaste);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClipboardDemo1().init();
    }

}
