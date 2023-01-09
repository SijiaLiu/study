package com.lsj.design.Observer;

public class Main {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.add(new ConcreteObserver1());
        subject.notifyObserver();

    }
}
