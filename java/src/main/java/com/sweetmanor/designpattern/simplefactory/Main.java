package com.sweetmanor.designpattern.simplefactory;

public class Main {

    public static void main(String[] args) {
        Bird bird = BirdFactory.createBird('o');
        bird.fly();
        bird = BirdFactory.createBird('e');
        bird.fly();
        bird = BirdFactory.createBird('s');
        bird.fly();
    }

}
