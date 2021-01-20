package com.lsj.design.strategy;

public class Context {

    private final StrategyContext strategyContext;

    public Context(StrategyContext strategyContext) {
        this.strategyContext = strategyContext;
    }

    public void display(String name) {
        System.out.println(name);
        strategyContext.display();
    }
}
