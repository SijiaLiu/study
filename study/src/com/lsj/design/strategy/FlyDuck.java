package com.lsj.design.strategy;

public class FlyDuck extends Duck {

    public FlyDuck(Fly fly) {
        super(fly);
    }

    public FlyDuck() {
    }

    @Override
    public void display() {
        System.out.println("一只会飞的鸭子");
    }

    @Override
    public void fly() {
        if (null == fly) {
            super.fly();
        } else {
            fly.fly(new FlyContext());
        }
    }
}
