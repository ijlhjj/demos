package com.sweetmanor.demo.gui.awt;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * AWT基本组件使用示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class AWTComponent {
    private final Frame frame = new Frame("AWT组件示例");

    public void init() {
        Box top = Box.createHorizontalBox();// 顶部容器
        frame.add(top);

        Box topLeft = Box.createVerticalBox();// 上左容器
        top.add(topLeft);

        TextArea ta = new TextArea(5, 20);
        topLeft.add(ta);

        Panel checkPanel = new Panel();
        topLeft.add(checkPanel);

        Choice colorChooser = new Choice(); // 颜色下拉列表框
        colorChooser.add("红色");
        colorChooser.add("绿色");
        colorChooser.add("蓝色");
        checkPanel.add(colorChooser);

        CheckboxGroup cbg = new CheckboxGroup(); // 单选框组，同一个组中的Checkbox只能有一个被选中（单选）
        Checkbox male = new Checkbox("男", cbg, true);
        Checkbox female = new Checkbox("女", cbg, false);
        checkPanel.add(male);
        checkPanel.add(female);

        Checkbox married = new Checkbox("是否已婚？", false); // 复选框组件
        checkPanel.add(married);

        List colorList = new List(6, true); // 颜色列表
        colorList.add("红色");
        colorList.add("绿色");
        colorList.add("蓝色");
        top.add(colorList);

        Panel bottom = new Panel();// 底部容器
        frame.add(bottom, BorderLayout.SOUTH);

        TextField name = new TextField(50);
        bottom.add(name);

        Button ok = new Button("确认");
        bottom.add(ok);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        FrameUtil.center(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AWTComponent().init();
    }

}
