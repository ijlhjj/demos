package com.sweetmanor.designpattern.strategy.duck;

public class Main {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck decoy = new DecoyDuck();
        decoy.performQuack();
        decoy.performFly();
    }

}
