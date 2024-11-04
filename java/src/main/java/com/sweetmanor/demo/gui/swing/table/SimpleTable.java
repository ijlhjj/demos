package com.sweetmanor.demo.gui.swing.table;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;

/**
 * 简单表格示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class SimpleTable {
    JFrame frame = new JFrame("简单表格");

    public void init() {
        // 定义一维数据作为列标题
        Object[] columnTitle = {"姓名", "年龄", "性别"};
        // 定义二维数组作为表格数据
        Object[][] tableData = {new Object[]{"李清照", 29, "女"}, new Object[]{"苏格拉底", 56, "男"}, new Object[]{"李白", 35, "男"}, new Object[]{"弄玉", 18, "女"}, new Object[]{"虎头", 2, "男"}};
        JTable table = new JTable(tableData, columnTitle);
        frame.add(new JScrollPane(table));

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleTable().init();
    }

}
