package com.lsj.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWait = lock.newCondition();

    private Condition emptyWait = lock.newCondition();

    private int cap = 10;

    private int count = 0;

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        new Thread(test2.new producer()).start();
        new Thread(test2.new consumer()).start();
    }

    class producer implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (count == cap) {
                    try {
                        fullWait.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                emptyWait.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }

    class consumer implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (count == 0) {
                    emptyWait.await();
                }
                count--;
                fullWait.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }
}
