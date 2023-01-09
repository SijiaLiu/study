package com.lsj.design.template;

public abstract class AbstractTemplate {

    //模板方法
    public void TemplateMethod() {
        SpecificMethod();
        abstractMethod1();
        abstractMethod2();
        if (SpecificMethod1()) {
            abstractMethod3();
        }
    }

    //具体方法
    public void SpecificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    // 钩子方法
    public boolean SpecificMethod1() {
        System.out.println("钩子方法...");
        return false;
    }

    //抽象方法1
    public abstract void abstractMethod1();

    //抽象方法2
    public abstract void abstractMethod2();

    //抽象方法3
    public abstract void abstractMethod3();
}
