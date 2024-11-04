package com.sweetmanor.demo.gui.swing.table;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

/**
 * 表格模型使用示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class DefaultTableModeDemo {
    JFrame frame = new JFrame("管理数据行、数据列");

    public void init() {
        final int COLUMN_COUNT = 5;
        DefaultTableModel defaultModel = new DefaultTableModel(COLUMN_COUNT, COLUMN_COUNT);
        for (int i = 0; i < COLUMN_COUNT; i++)
            for (int j = 0; j < COLUMN_COUNT; j++)
                defaultModel.setValueAt("老单元格值 " + i + "  " + j, i, j);

        JTable table = new JTable(defaultModel);
        frame.add(new JScrollPane(table));

        // 为窗口安装菜单
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu tableMenu = new JMenu("管理");
        menuBar.add(tableMenu);

        ArrayList<TableColumn> hiddenColumns = new ArrayList<>(); // 用于保存被隐藏列的List集合

        // 添加隐藏菜单项
        JMenuItem hideColumnsItem = new JMenuItem("隐藏选中列");
        hideColumnsItem.addActionListener(e -> {
            // 获取所有选中列的索引
            int[] selected = table.getSelectedColumns();
            TableColumnModel columnModel = table.getColumnModel();
            // 依次把每一个选中的列隐藏起来，并使用List保存这些列
            for (int i = selected.length - 1; i >= 0; i--) {
                TableColumn column = columnModel.getColumn(selected[i]);
                table.removeColumn(column);
                hiddenColumns.add(column);
            }
        });
        tableMenu.add(hideColumnsItem);

        // 添加显示隐藏菜单项
        JMenuItem showColumnsItem = new JMenuItem("显示隐藏项");
        showColumnsItem.addActionListener(e -> {
            // 把所有隐藏的列依次显示出来
            for (TableColumn tc : hiddenColumns) {
                table.addColumn(tc);
            }
            hiddenColumns.clear();
        });
        tableMenu.add(showColumnsItem);

        // 添加插入菜单项
        JMenuItem addColumnItem = new JMenuItem("插入选中列");
        addColumnItem.addActionListener(e -> {
            int[] selected = table.getSelectedColumns();
            TableColumnModel model = table.getColumnModel();
            for (int i = selected.length - 1; i >= 0; i--) {
                TableColumn column = model.getColumn(selected[i]);
                table.addColumn(column);
            }
        });
        tableMenu.add(addColumnItem);

        // 添加增加行菜单项
        JMenuItem addRowItem = new JMenuItem("增加行");
        addRowItem.addActionListener(e -> {
            String[] newCells = new String[COLUMN_COUNT];
            for (int i = 0; i < newCells.length; i++) {
                newCells[i] = "新单元格值 " + defaultModel.getRowCount() + "  " + i;
            }
            defaultModel.addRow(newCells);
        });
        tableMenu.add(addRowItem);

        // 添加删除行菜单项
        JMenuItem removeRowsItem = new JMenuItem("删除选中行");
        removeRowsItem.addActionListener(e -> {
            int[] selected = table.getSelectedRows();
            for (int i = selected.length - 1; i >= 0; i--) {
                defaultModel.removeRow(selected[i]);
            }
        });
        tableMenu.add(removeRowsItem);

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DefaultTableModeDemo().init();
    }

}
