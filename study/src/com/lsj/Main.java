package com.lsj;

import java.util.PriorityQueue;


public class Main {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        queue.add(6);
        queue.add(5);
        queue.add(4);
        System.out.println(queue.peek());
    }
}
