package com.sweetmanor.designpattern.decorator;

/**
 * 装饰模式示例
 *
 * @author ijlhjj
 * @version 1.0 2015-12-27
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
