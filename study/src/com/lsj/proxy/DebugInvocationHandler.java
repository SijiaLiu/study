package com.lsj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {

    // 代理类中真正运行的对象
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 代理类调用的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method before : " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("invoke method after : " + method.getName());
        return result;
    }
}
