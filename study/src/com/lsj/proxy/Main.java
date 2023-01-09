package com.lsj.proxy;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Main {


    public static void main(String[] args) {
        // 为什么要代理？
        // 代理类的实现是真正的业务逻辑，可能每个业务都完全不一样，但我想给每一个代理类做一些通用的事，比如日志，耗时
        // 那如何创建代理呢？
        // 当然是要用反射了。Proxy类提供了创建instance，也就是代理类的方法，按要求把代理类，以及给代理类加逻辑的InvocationHandle传入即可
        // 如何加逻辑？
        // JDK提供了 InvocationHandle 里面的invoke 方法可以加逻辑
        // 每次都用Proxy写一大堆相同的，怎么精简？
        // 弄一个工厂，每次传入真正要代理的类就行
        SmsService instance = (SmsService) JdkProxyFactory.getInstance(new SmsServiceImpl());
        instance.eat("success");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

    }
}
