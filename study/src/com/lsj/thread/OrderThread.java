package com.lsj.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序线程 目标让多个线程按想要的顺序执行
 */
public class OrderThread {

    /**
     * 第一种办法 用锁，多个线程去获取同一个锁，获取到的话就执行，获取不到就等待
     * 获取到锁的线程执行完之后唤醒所有等待线程，让他们去抢
     * 怎么控制顺序呢？ 用一个变量控制当前应该能执行的线程是哪个，期间会疯狂抢锁
     */
    static int count = 0;
    static final Object obj = new Object();

    static class ObjLockThread extends Thread {
        // 表示线程号
        int seq;

        public ObjLockThread (int seq) {
            this.seq = seq;
        }
        @Override
        public void run() {
            while (count < 3) {
                // 给对象上锁
                synchronized (obj) {
                    // 虽然当前线程拿到了锁，但要判断是否轮到当前线程执行
                    if (count % 3 == seq) {
                        System.out.println(seq);
                        count++;
                        obj.notifyAll();
                    } else if (count > 3) {
                        System.out.println(count);
                        currentThread().interrupt();
                    } else {
                        // 当前线程获取不到锁 就进入等待
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 第二种 利用 Thread.join() 方法 这个方法会阻塞当前线程，等待子线程执行完之后再继续执行
     * 也就是会先执行 Thread 的 run() 方法
     */
    static class Thread1 implements Runnable {
        Thread parent;
        String out;
        public Thread1(String out, Thread parent) {
            this.out = out;
            this.parent = parent;
        }

        @Override
        public void run() {
            if (parent != null) {
                try {
                    parent.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(out);
        }
    }

    static class Thread2 extends Thread {
        String out;
        public Thread2(String out) {
            this.out = out;
        }

        @Override
        public void run() {
            try {
                System.out.println("sync");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(out);
        }
    }
    static class Thread22 extends Thread {
        String out;
        public Thread22(String out) {
            this.out = out;
        }

        @Override
        public void run() {
            try {
                System.out.println("cSyncA");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(out);
        }
    }


    static class Thread3 implements Callable {
        String out;
        public Thread3(String out) {
            this.out = out;
        }

        @Override
        public String call() throws Exception {
            System.out.println(out);
            return out;
        }
    }

    /**
     * 利用 ReentrantLock 线程间互相唤醒
     */
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition startCondition = reentrantLock.newCondition();
    static Condition endCondition = reentrantLock.newCondition();
    static class Thread4 extends Thread {
        Condition startCondition;
        Condition endCondition;
        String name;
        public Thread4(Condition startCondition,
                Condition endCondition, String name) {
            this.startCondition = startCondition;
            this.endCondition = endCondition;
            this.name = name;
        }
        @Override
        public void run() {
            // 阻塞
            if (startCondition != null) {
                reentrantLock.lock();
                try {
                    // 阻塞 知道别人唤醒
                    startCondition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
            //继续往下执行
            System.out.println(name + "开始执行");
            //唤醒
            if (endCondition != null) {
                reentrantLock.lock();
                try {
                    // 唤醒别人
                    endCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    /**
     * ReentrantLock 实现公平锁
     */
    static ReentrantLock reentrantFairLock = new ReentrantLock(true);
    static class Thread4_1 extends Thread {
        public Thread4_1(String name) {
            this.name = name;
        }
        String name;


        @Override
        public void run() {
            reentrantFairLock.lock();
            System.out.println(name);
            reentrantFairLock.unlock();
        }
    }

    /**
     * 测试 主线程中断子线程
     */
    static class Thread5 extends Thread {

        String name;

        public Thread5(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("响应中断，跳出循环，停止线程");
            }
        }

        private void throwInMethod() throws InterruptedException  {
            System.out.println("执行业务");
            Thread.sleep(2000);
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("通过锁的方式 控制三个线程的执行顺序");
        ObjLockThread o = new ObjLockThread(0);
        ObjLockThread o1 = new ObjLockThread(1);
        ObjLockThread o2 = new ObjLockThread(2);
        o.start();
        o1.start();
        o2.start();
        Thread.sleep(1000);

        System.out.println("按顺序调用三个线程，每个线程都先自己阻塞");
        // 利用 join() 方法
        Thread2 a = new Thread2("A");
        Thread22 b = new Thread22("B");
        Thread2 c = new Thread2("C");
        a.start();
        // 利用join 保证了线程a先执行完
        a.join();
        b.start();
        b.join();
        c.start();
        c.join();
        Thread.sleep(1000);


        System.out.println("另外一个也是利用join 但是在子线程中判断有没有父线程，如果有的话就让父线程先执行完");
        Thread a1 = new Thread(new Thread1("A", null));
        Thread a2 = new Thread(new Thread1("B", a1));
        Thread a3 = new Thread(new Thread1("C", a2));
        a3.start();
        a2.start();
        a1.start();
        Thread.sleep(1000);


        System.out.println("第三种 利用 java8的 直接按顺序执行");
        CompletableFuture.runAsync(new Thread2("A")::start).
                thenRun(new Thread2("B")::start)
                .thenRun(new Thread2("C")::start);
        Thread.sleep(1000);


        System.out.println("第四种，利用ReentrantLock的condition 其实和上边的锁差不多");
        Thread4 a5 = new Thread4(null, startCondition, "A");
        Thread4 b2 = new Thread4(startCondition, endCondition, "B");
        Thread4 c2 = new Thread4(endCondition, null, "C");
        c2.start();
        b2.start();
        a5.start();
        Thread.sleep(1000);

        System.out.println("第4_1种，利用ReentrantLock的公平锁");
        Thread4_1 a6 = new Thread4_1("A");
        Thread4_1 b6 = new Thread4_1("B");
        Thread4_1 c6 = new Thread4_1("C");
        c6.start();
        a6.start();
        b6.start();
        Thread.sleep(1000);


        System.out.println("构造有返回值的异步线程");
        FutureTask<String> a4 = new FutureTask<String>(new Thread3("A"));
        Thread thread = new Thread(a4);
        thread.start();
        System.out.println(a4.get());
        Thread.sleep(1000);

        System.out.println("主线程打断子线程");
        Thread5 thread5 = new Thread5("打算子线程测试");
        thread5.start();
        Thread.sleep(100);
        thread5.interrupt();
        System.out.println("打断成功");

        System.out.println("测试核心线程数会被回收");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);
        boolean b1 = executor.allowsCoreThreadTimeOut();
        System.out.println(b1);
    }

}
