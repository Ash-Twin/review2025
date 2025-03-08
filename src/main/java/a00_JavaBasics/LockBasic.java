package a00_JavaBasics;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Chenyu Liu
 * @since 3/8/25 Saturday
 **/

public class LockBasic {

    public static void main(String[] args) throws Exception {
        SynchronizedCounter sc = new SynchronizedCounter();
        ReentrantLockCounter rlc = new ReentrantLockCounter();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(sc::increment);
            threads[i].start();
            threads[20 + i] = new Thread(rlc::increment);
            threads[20 + i].start();
        }
        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }
        System.out.println(sc.getCount());
        System.out.println(rlc.getCount());
    }
}

class SynchronizedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class ReentrantLockCounter {
    private int count = 0;
    // 不用ThreadLocal的话，lock对于任何ReentrantLockCounter对象，访问的是同一个锁的引用
    // ThreadLocal就是用于这样的对象
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();

        }
    }

    public int getCount() {
        return count;
    }
}
