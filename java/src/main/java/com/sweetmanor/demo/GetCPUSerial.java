package com.sweetmanor.demo;

import java.io.IOException;

/**
 * 获取CPU编号 <br />
 * 每个CPU都有一个独一无二的ID号(即CPUID)， CPUID是在制造CPU的时候，由厂家置入到CPU内部的。
 *
 * @author wenz
 * @version 1.0 2020-01-26
 */
public class GetCPUSerial {

    public static void main(String[] args) throws IOException {
        /* 通过调用Windows系统的内置命令实现 */
        ProcessBuilder builder = new ProcessBuilder("wmic", "cpu", "get", "ProcessorId");
        builder.inheritIO();
        builder.start();
    }

}
