package com.lsj.thread;

public interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);

}
