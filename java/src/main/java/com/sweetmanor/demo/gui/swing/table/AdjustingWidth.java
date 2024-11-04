package com.sweetmanor.demo.gui.swing.table;

import com.sweetmanor.utils.FrameUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * 自定义表格列宽示例
 *
 * @author ijlhjj
 * @version 1.0 2014-08-26
 */
public class AdjustingWidth {
    JFrame frame = new JFrame("调整表格列宽");

    // 表格的宽度调整方式
    int[] adjustModes = new int[]{JTable.AUTO_RESIZE_OFF, JTable.AUTO_RESIZE_NEXT_COLUMN, JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS, JTable.AUTO_RESIZE_LAST_COLUMN, JTable.AUTO_RESIZE_ALL_COLUMNS};
    // 表格的选择方式
    int[] selectModes = new int[]{ListSelectionModel.MULTIPLE_INTERVAL_SELECTION, ListSelectionModel.SINGLE_INTERVAL_SELECTION, ListSelectionModel.SINGLE_SELECTION};

    public void init() {
        Object[] columnTitle = {"姓名", "年龄", "性别"}; // 定义一维数据作为列标题
        // 定义二维数组作为表格数据
        Object[][] tableData = {new Object[]{"李清照", 29, "女"}, new Object[]{"苏格拉底", 58, "男"}, new Object[]{"李白", 35, "男"}, new Object[]{"弄玉", 18, "女"}, new Object[]{"虎头", 2, "男"}};
        JTable table = new JTable(tableData, columnTitle);

        // 组装菜单栏一级菜单
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu adjustModeMenu = new JMenu("调整方式");
        menuBar.add(adjustModeMenu);
        JMenu selectModeMenu = new JMenu("选择方式");
        menuBar.add(selectModeMenu);
        JMenu selectUnitMenu = new JMenu("选择单元");
        menuBar.add(selectUnitMenu);

        // 组装“调整方式”菜单
        // 定义5个单选按钮，用以控制表格的宽度调整方式
        JRadioButtonMenuItem[] adjustModesItem = new JRadioButtonMenuItem[5];
        adjustModesItem[0] = new JRadioButtonMenuItem("只调整表格");
        adjustModesItem[1] = new JRadioButtonMenuItem("只调整下一列");
        adjustModesItem[2] = new JRadioButtonMenuItem("平均调整余下列");
        adjustModesItem[3] = new JRadioButtonMenuItem("只调整最后一列");
        adjustModesItem[4] = new JRadioButtonMenuItem("平均调整所有列");

        ButtonGroup adjustBg = new ButtonGroup();
        for (int i = 0; i < adjustModesItem.length; i++) {
            adjustBg.add(adjustModesItem[i]);
            adjustModeMenu.add(adjustModesItem[i]);

            final int index = i;
            adjustModesItem[i].addActionListener(e -> { // 为菜单添加监听器
                if (adjustModesItem[index].isSelected())
                    table.setAutoResizeMode(adjustModes[index]);
            });
        }
        adjustModesItem[2].setSelected(true); // 默认选中第3个菜单，即对应表格默认的宽度调整方式

        // 组装“选择方式”菜单
        // 定义3个单选按钮，用以控制表格的选择方式
        JRadioButtonMenuItem[] selectModesItem = new JRadioButtonMenuItem[3];
        selectModesItem[0] = new JRadioButtonMenuItem("无限制");
        selectModesItem[1] = new JRadioButtonMenuItem("单独的连续区");
        selectModesItem[2] = new JRadioButtonMenuItem("单选");

        ButtonGroup selectBg = new ButtonGroup();
        for (int i = 0; i < selectModesItem.length; i++) {
            selectBg.add(selectModesItem[i]);
            selectModeMenu.add(selectModesItem[i]);

            final int index = i;
            selectModesItem[i].addActionListener(e -> { // 为菜单添加监听器
                if (selectModesItem[index].isSelected())
                    table.getSelectionModel().setSelectionMode(selectModes[index]);
            });
        }
        selectModesItem[0].setSelected(true); // 默认选中第一个菜单项，即对应表格默认的选择方式

        // 组装“选择单元”菜单
        // 定义3个复选框，用以控制表格的单元选择
        JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("选择行");
        JCheckBoxMenuItem columnsItem = new JCheckBoxMenuItem("选择列");
        JCheckBoxMenuItem cellsItem = new JCheckBoxMenuItem("选择单元格");
        rowsItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());

        rowsItem.addActionListener(e -> {
            table.clearSelection();// 清空选择状态
            table.setRowSelectionAllowed(rowsItem.isSelected()); // 如果该菜单处于选中状态，设置表格的选择单元是行
            cellsItem.setSelected(table.getCellSelectionEnabled()); // 如果行、列同时被选中，其实质是选择单元格
        });
        selectUnitMenu.add(rowsItem);

        columnsItem.addActionListener(e -> {
            table.clearSelection();
            table.setColumnSelectionAllowed(columnsItem.isSelected());
            cellsItem.setSelected(table.getCellSelectionEnabled());
        });
        selectUnitMenu.add(columnsItem);

        cellsItem.addActionListener(e -> {
            table.clearSelection();
            table.setCellSelectionEnabled(cellsItem.isSelected());
            rowsItem.setSelected(table.getRowSelectionAllowed());
            columnsItem.setSelected(table.getColumnSelectionAllowed());
        });
        selectUnitMenu.add(cellsItem);

        // 分别获取表格的三个表格列，并设置三列的最小宽度、最佳宽度、最大宽度
        TableColumn nameColumn = table.getColumn(columnTitle[0]);
        nameColumn.setMinWidth(100);
        TableColumn ageColumn = table.getColumn(columnTitle[1]);
        ageColumn.setPreferredWidth(50);
        TableColumn genderColumn = table.getColumn(columnTitle[2]);
        genderColumn.setMaxWidth(50);

        frame.add(new JScrollPane(table));

        frame.pack();
        FrameUtil.center(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdjustingWidth().init();
    }

}
