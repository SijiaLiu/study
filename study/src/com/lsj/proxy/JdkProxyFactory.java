package com.lsj.proxy;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    // 工厂 创建任何对象的实例
    public static Object getInstance(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(), // 目标代理类的类加载器
                object.getClass().getInterfaces(),  // 目标代理类里面的接口，也就是方法
                new DebugInvocationHandler(object)); // 代理对象对应的自定义 InvocationHandler
    }
}
