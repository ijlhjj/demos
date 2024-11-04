package com.sweetmanor.demo

/**
 * 获取CPU逻辑处理数量
 *
 * @version 1.0 2023-09-09
 * @author wenz
 */
fun main() {
    val processors = Runtime.getRuntime().availableProcessors();
    println(processors)
}
