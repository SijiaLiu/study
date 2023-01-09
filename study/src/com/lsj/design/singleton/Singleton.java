package com.lsj.design.singleton;

/**
 * 单例的实现主要是通过以下三个步骤：
 *
 * 将类的构造方法定义为私有方法。这样其他类的代码就无法通过调用该类的构造方法来实例化该类的对象，只能通过该类提供的静态方法来得到该类的唯一实例。
 * 定义一个私有的类的静态实例。
 * 提供一个公有的获取实例的静态方法。
 */
public class Singleton {

    private volatile static Singleton instance;
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
