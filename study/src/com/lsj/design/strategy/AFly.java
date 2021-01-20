package com.lsj.design.strategy;

public class AFly implements Fly{

    @Override
    public void fly(StrategyContext strategyContext) {
        strategyContext.display();
    }
}
