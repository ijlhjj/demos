package com.sweetmanor.designpattern.strategy.duck;

public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

}
