package com.sweetmanor.designpattern.adapter;

/**
 * 适配器模式示例
 *
 * @author wenz
 * @version 1.0 2016-06-19
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("自定义普通的汽车：");
        Car car = new Car("Adui", 60);
        System.out.println(car);

        System.out.println("自定义GPS汽车：");// 自定义GPSCar可以当作GPS对象使用
        GPSCar gpsCar = new GPSCar("Adui", 60);
        System.out.println(gpsCar);
    }

}
