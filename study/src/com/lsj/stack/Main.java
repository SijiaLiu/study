package com.lsj.stack;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        MyStack myStack = new MyStack(10);

        System.out.println(myStack.isEmpty());
        myStack.push("liu");
        myStack.push("si");
        myStack.push("jia");
        System.out.println(myStack.pop());

        DStack dStack = new DStack(5);
        dStack.push("liu", 1);
        dStack.push("si", 1);
        dStack.push("jia", 2);
        dStack.push("si", 2);
        dStack.push("jia", 1);
        dStack.push("liu", 2);
        System.out.println(dStack.pop(1));
        System.out.println(dStack.pop(2));

        ValidBrackets validBrackets = new ValidBrackets();
        System.out.println(validBrackets.isValid("()"));


        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));

        EvalRPN evalRPN = new EvalRPN();
        System.out.println(evalRPN.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
