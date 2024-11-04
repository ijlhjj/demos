package com.sweetmanor.designpattern.simplefactory;

/**
 * 简单工厂模式示例
 *
 * @author ijlhjj
 * @version 1.0 2015-12-27
 */
public class BirdFactory {

    public static Bird createBird(char name) {
        // 根据传入参数确定创建那个类的对象
        switch (name) {
            case 'e':
                return new Eagle();
            case 'o':
                return new Ostrich();
            case 's':
                return new Sparrow();
            default:
                return null;
        }
    }

}
