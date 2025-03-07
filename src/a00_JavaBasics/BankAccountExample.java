package a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class BankAccountExample {

    // 竞态条件问题示例（不使用任何同步机制）
    static class Account {
        int balance;  // 没有volatile关键字修饰，多个线程可能看不到最新的余额

        public void deposit(int amount) {
            balance += amount;
//            System.out.println("存款：" + amount + "，当前余额：" + balance);
        }

        public void withdraw(int amount) {
            balance -= amount;
//            System.out.println("取款：" + amount + "，当前余额：" + balance);
        }
    }

    // 使用synchronized关键字修复竞态条件
    static class SynchronizedAccount extends Account {
        @Override
        public synchronized void deposit(int amount) {
            super.deposit(amount);
        }

        @Override
        public synchronized void withdraw(int amount) {
            super.withdraw(amount);
        }
    }

    // 使用ReentrantLock进行显式锁定
    static class ReentrantAccount extends Account {
        private Lock lock = new java.util.concurrent.locks.ReentrantLock();

        @Override
        public void deposit(int amount) {
            lock.lock();
            try {
                super.deposit(amount);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void withdraw(int amount) {
            lock.lock();
            try {
                super.withdraw(amount);
            } finally {
                lock.unlock();
            }
        }
    }

    // 使用volatile关键字和AtomicInteger
    static class VolatileAccount {
        volatile int balance;  // 声明为volatile，确保可见性

        public void setBalance(int amount) {
            balance = amount;
//            System.out.println("设置余额：" + amount);
        }

        public int getBalance() {
            return balance;
        }
    }

    static class AtomicAccount {
        AtomicInteger balance = new AtomicInteger(0);  // 原子操作，线程安全

        public void deposit(int amount) {
            balance.addAndGet(amount);
//            System.out.println("原子存款：" + amount + "，当前余额：" + balance.get());
        }

        public void withdraw(int amount) {
            balance.addAndGet(-amount);
//            System.out.println("原子取款：" + amount + "，当前余额：" + balance.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试竞态条件问题
        Account account = new Account();
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1);
            }
        });
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(1);
            }
        });
        depositThread.start();
        withdrawThread.start();
        depositThread.join();
        withdrawThread.join();
        System.out.println("普通账户最终余额：" + account.balance);

        // 使用synchronized修复
        SynchronizedAccount syncAccount = new SynchronizedAccount();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncAccount.deposit(2);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                syncAccount.withdraw(2);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("同步账户最终余额：" + syncAccount.balance);

        // 使用ReentrantLock
        ReentrantAccount reentrantAccount = new ReentrantAccount();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                reentrantAccount.deposit(3);
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                reentrantAccount.withdraw(3);
            }
        });
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("ReentrantLock账户最终余额：" + reentrantAccount.balance);

        // 使用volatile关键字
        // volatile会保证其修饰变量每次修改都会被同步回主内存RAM main memory
        // 否则会出现只更新CPU cache的情况
        // 使用场景包括共享变量、状态标志和计数器等，但不能替代锁机制来保证原子性和一致性。
        VolatileAccount volatileAccount = new VolatileAccount();
        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                volatileAccount.setBalance(4);
            }
        });
        Thread t6 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
//                System.out.println("余额：" + volatileAccount.getBalance());
            }
        });
        t5.start();
        t6.start();
        t5.join();
        t6.join();

        // 使用AtomicInteger
        AtomicAccount atomicAccount = new AtomicAccount();
        Thread t7 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicAccount.deposit(5);
            }
        });
        Thread t8 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicAccount.withdraw(5);
            }
        });
        t7.start();
        t8.start();
        t7.join();
        t8.join();
        System.out.println("原子账户最终余额：" + atomicAccount.balance);

    }

}
