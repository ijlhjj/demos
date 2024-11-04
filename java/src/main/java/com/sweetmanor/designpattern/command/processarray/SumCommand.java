package com.sweetmanor.designpattern.command.processarray;

/**
 * 对元素求和并输出
 */
public class SumCommand implements Command {

    @Override
    public void process(int[] array) {
        int sum = 0;
        for (int i : array)
            sum += i;
        System.out.println("数组元素的总和是：" + sum);
    }

}
