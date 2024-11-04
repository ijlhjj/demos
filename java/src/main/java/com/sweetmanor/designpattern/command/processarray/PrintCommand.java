package com.sweetmanor.designpattern.command.processarray;

/**
 * 逐个输出数组元素
 */
public class PrintCommand implements Command {

    @Override
    public void process(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.println("元素 " + i + " 值为：" + array[i]);
    }

}
