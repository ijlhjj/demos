package com.sweetmanor.designpattern.observer.custom;

/**
 * 观察者模式示例：自定义实现观察者模式
 *
 * @author ijlhjj
 * @version 1.0 2015-12-27
 */
public interface Subject {

    void registerObserver(Observer o);// 注册观察者

    void removeObserver(Observer o);// 删除观察者

    void notifyObservers();// 通知所有观察者

}
