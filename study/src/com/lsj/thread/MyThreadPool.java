package com.lsj.thread;

import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;


public class MyThreadPool implements Executor {

    private int corePoolSize;

    private LinkedBlockingQueue<Runnable> workQueue;

    private ThreadFactory threadFactory;

    public MyThreadPool(int corePoolSize, LinkedBlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this.corePoolSize = corePoolSize;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
    }

    // 保存所有的worker
    private final HashSet<Worker> workers = new HashSet<Worker>();

    @Override
    public void execute(Runnable command) {
        // 如果工作线程少于corePoolSize，新增线程执行
        if (workers.size() < corePoolSize) {
            addWorker(command);
            return;
        }
        // 否则添加到任务队列
        if (workQueue.offer(command)) {
            return;
        }
        // 任务队列满了：拒绝
        throw new RejectedExecutionException();
    }

    /**
     * 添加工作者
     * @param command
     */
    private void addWorker(Runnable command) {
        Worker w = new Worker(command);
        final Thread t = w.thread;
        t.start();
        workers.add(w);
    }

    // 拥有工作任务的工作者
    private final class Worker implements Runnable {
        // 线程
        Thread thread;
        // 第一个任务
        Runnable firstTask;
        // 工作者初始化
        Worker(Runnable task) {
            // 保存第一个任务
            firstTask = task;
            // 初始化工作台
            thread = threadFactory.newThread(this);
        }

        // 工作者的任务计划
        @Override
        public void run() {
            Runnable task = firstTask;
            firstTask = null;
            // 第一个任务未执行执行第一个，执行完后去队列获取任务，执行结束后再次尝试获取
            while (task != null || (task = getTask()) != null) {
                try {
                    task.run();
                } finally {
                    // 当前任务置空重新处理
                    task = null;
                }
            }
        }

        // 从队列获取任务
        private Runnable getTask() {
            try {
                return workQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  null;
        }
    }
}
