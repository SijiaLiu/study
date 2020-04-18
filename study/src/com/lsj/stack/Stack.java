package com.lsj.stack;

/**
 * 数组实现的栈
 */
public class Stack {

    public int top = -1;

    public Object[] elementData;

    public Stack(int size) {
        elementData = new Object[size];
    }

    public void push(Object n) {
        if (top == -1) {
            elementData[++top] = n;
            return;
        }
        if (top >= elementData.length) {
            System.out.println("stack is full!");
            return;
        }
        elementData[++top] = n;
    }

    public Object pop() {
        if (top == -1) {
            System.out.println("stack is empty");
            return -1;
        }
        Object oldValue = elementData[top];
        elementData[top--] = null;
        return oldValue;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
