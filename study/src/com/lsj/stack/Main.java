package com.lsj.stack;

public class Main {
    public static void main(String[] args) {

        Stack stack = new Stack(10);

        System.out.println(stack.isEmpty());
        stack.push("liu");
        stack.push("si");
        stack.push("jia");
        System.out.println(stack.pop());

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
    }
}
