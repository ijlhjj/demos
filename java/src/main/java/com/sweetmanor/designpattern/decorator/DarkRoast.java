package com.sweetmanor.designpattern.decorator;

/**
 * 深焙(bei)咖啡
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 0.99;
    }

}
