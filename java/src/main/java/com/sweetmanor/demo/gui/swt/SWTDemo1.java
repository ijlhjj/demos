package com.sweetmanor.demo.gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

/**
 * SWT窗体示例
 *
 * @author ijlhjj
 * @version 1.0 2015-01-09
 */
public class SWTDemo1 {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);

        Label label = new Label(shell, SWT.NONE);
        label.setText("姓名:");

        Text text = new Text(shell, SWT.BORDER);
        text.setLayoutData(new RowData(100, SWT.DEFAULT));

        Button ok = new Button(shell, SWT.PUSH);
        ok.setText("确认");
        ok.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println("OK");
            }
        });

        Button cancel = new Button(shell, SWT.PUSH);
        cancel.setText("取消");
        cancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Cancel");
            }
        });

        shell.setDefaultButton(cancel);// 窗体默认动作，按Enter健将执行此动作
        shell.setLayout(new RowLayout());// 窗体布局
        shell.pack();// 自动调整大小
        shell.open();

        // 循环事件监听器
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        display.dispose();// 释放资源
    }

}
