package com.sweetmanor.designpattern.adapter;

/**
 * 普通汽车类
 *
 * @author wenz
 * @version 1.0 2016-06-19
 */
public class Car {
    private String name;
    private int speed;

    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("车名:" + name + ", ");
        sb.append("速度：" + speed + "千米/小时");
        return sb.toString();
    }

}
