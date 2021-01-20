package com.lsj.design.strategy;

public abstract class Duck {

    public Fly fly;

    public Duck(Fly fly) {
        this.fly = fly;
    }

    public Duck() {
    }

    public abstract void display();

    public void fly() {
        System.out.println("默认飞");
    }
}
