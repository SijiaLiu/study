package com.lsj.stack;

/**
 * 一个数组 实现双端都可插入的栈
 */
public class DStack {

    public int top1 = -1;

    public Object[] elementData;

    public int top2;

    public DStack(int n) {
        elementData = new Object[n];
        top2 = elementData.length;
    }

    public void push(Object o, int tag) {
        if (top2 - top1 == 1) {
            System.out.println("stack is full");
            return;
        }
        if (tag == 1) {
            elementData[++top1] = o;
        } else {
            elementData[--top2] = o;
        }
    }

    public Object pop(int tag) {
        if (isEmpty()) {
            System.out.println("stack is empty");
            return null;
        }
        Object value;
        if (tag == 1) {
            if (top1 == -1) {
                System.out.println("stack is empty");
                return null;
            }
            value = elementData[top1];
            elementData[top1--] = null;
        } else {
            if (top2 == elementData.length) {
                System.out.println("stack is empty");
                return null;
            }
            value = elementData[top2];
            elementData[top2++] = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return top1 == -1 && top2 == elementData.length;
    }
}
