package com.lsj.design;


import com.lsj.design.strategy.AFly;
import com.lsj.design.strategy.Context;
import com.lsj.design.strategy.FlyContext;
import com.lsj.design.strategy.FlyDuck;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        FlyDuck f1 = new FlyDuck(new AFly());
        f1.fly();
        FlyDuck f2 = new FlyDuck();
        f2.fly();

        Map<String, String> map = new HashMap<>();
        map.computeIfAbsent("key", function);
        map.computeIfAbsent("key", function);
        System.out.println(map.get("key"));

        Context fly = new Context(new FlyContext());
        fly.display("lsj");
    }

    private static Function<String, String> function = Main::readFromDB;

    private static String readFromDB(String param) {
        System.out.println("read");
        return param;
    }
}
