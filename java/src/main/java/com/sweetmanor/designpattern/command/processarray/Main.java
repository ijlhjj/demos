package com.sweetmanor.designpattern.command.processarray;

import com.sweetmanor.common.RandomInt;

public class Main {

    public static void main(String[] args) {
        int[] array = RandomInt.random(); // 生成随机数组
        ProcessArray pa = new ProcessArray();
        pa.process(array, new PrintCommand()); // 调用打印命令
        pa.process(array, new SumCommand());// 调用求和命令
    }

}
