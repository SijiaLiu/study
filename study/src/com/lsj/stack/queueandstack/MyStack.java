package com.lsj.stack.queueandstack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 */
public class MyStack {

    public Queue<Integer> queue;


    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        // 遍历队列 把队列的头元素都加到队列的末尾
        while (size-- > 1) {
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
