package com.sweetmanor.designpattern.adapter;

/**
 * GPSCar继承了Car类并实现了GPS接口，可以当作GPS对象使用
 *
 * @author wenz
 * @version 1.0 2016-06-19
 */
public class GPSCar extends Car implements GPS {

    public GPSCar(String name, int speed) {
        super(name, speed);
    }

    @Override
    public Point getLocation() {
        int speed = super.getSpeed();
        return new Point(speed, speed);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Point point = getLocation();
        sb.append(", 坐标：(" + point.getX() + ", " + point.getY() + ")");
        return sb.toString();
    }

}
