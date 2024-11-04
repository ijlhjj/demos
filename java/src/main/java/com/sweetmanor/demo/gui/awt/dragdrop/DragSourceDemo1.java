package com.sweetmanor.demo.gui.awt.dragdrop;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

/**
 * Drag拖放源示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class DragSourceDemo1 {
    JFrame frame = new JFrame("Drag拖放源示例");

    public void init() {
        final JLabel source = new JLabel("AWT的拖放支持。\n将文本域的内容拖入其他程序。\n");
        frame.add(source);

        DragSource dragSource = DragSource.getDefaultDragSource();
        // 将source转换成拖放源
        dragSource.createDefaultDragGestureRecognizer(source, DnDConstants.ACTION_COPY_OR_MOVE, dge -> {
            String text = source.getText();// 将JLabel里的文本包装成Transferable对象
            Transferable transferable = new StringSelection(text);
            dge.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), transferable); // 继续拖放操作
        });

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DragSourceDemo1().init();
    }

}
