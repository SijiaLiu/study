package com.lsj.thread;

import java.util.concurrent.TimeUnit;

public class TestPool2 {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1,
                TimeUnit.NANOSECONDS, 2, (queue, task) -> {
            // 2) 带超时等待
            queue.offer(task, 15, TimeUnit.MILLISECONDS);
            task.run();
        });

        for (int i = 0; i < 10; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j);
            });
        }

    }
}
