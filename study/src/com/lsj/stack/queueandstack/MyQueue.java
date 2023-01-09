package com.lsj.stack.queueandstack;

import java.util.Stack;

/**
 * 使用栈实现'
 */
public class MyQueue {

    public Stack<Integer> inStack;

    public Stack<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     * 入队都是往入栈塞
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     * 出队都是从出栈出，出栈空的时候，要把入栈的元素全部转移到出栈中，由出栈统一出队
     */
    public int pop() {
        if (inStack.isEmpty() && outStack.isEmpty()) {
            return 0;
        }
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (inStack.isEmpty() && outStack.isEmpty()) {
            return 0;
        }
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
