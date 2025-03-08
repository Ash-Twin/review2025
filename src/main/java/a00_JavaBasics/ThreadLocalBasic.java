package a00_JavaBasics;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author Chenyu Liu
 * @since 3/7/25 Friday
 **/

public class ThreadLocalBasic {

    // 注意这里尽管用static修饰，但是结果仍旧不同，ThreadLocal是
    volatile static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static class MyThread extends Thread {
        @Override
        public void run() {
            threadLocal.set(4);
            Integer integer = threadLocal.get();
            System.out.println("t1:" + integer);
        }
    }

    public static class MyThread2 implements Callable<Integer> {

        @Override
        public Integer call() {
            Integer integer = threadLocal.get();
            System.out.println("t3:" + integer);
            return integer;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 第一种创建线程的方式：继承Thread类，重写run方法，创建实例
        Thread t1 = new MyThread();

        // 第二种创建线程的方式： 直接new Thread，用lambda方法实现Runnable接口的run方法
        Thread t2 = new Thread(() -> {
            Integer integer = threadLocal.get();
            System.out.println("t2:" + integer);
            threadLocal.set(6);
        }); // 简化写法使用lambda，也可以先 new Runnable然后再实现run方法，后new Thread初始化runnable参数


        t1.start();
        t1.join();
        t2.start(); // t2
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> res = executor.submit(new MyThread2());
        executor.shutdown();
    }

    // ThreadLocal内存泄露主要是因为ThreadLocal的生命周期与Thread绑定在一起
    // 线程一直存活，比如在线程池里，那么线程将一直持有ThreadLocalMap对ThreadLocal对象的引用，无法gc
    // ThreadLocalMap使用Entry[] 来保存所有ThreadLocal引用, initialCapacity = 16且必须为2的指数
    // Entry 继承于WeakReference<ThreadLocal> 目的是ThreadLocal对象没有强引用时能够被gc
    // 这里弱引用只能保证null时被回收，但是如果线程一直存活，该Entry[]中对应的不会是null，所以不会gc导致内存泄露
    // 拓展：同时这里可以去根据ThreadLocal与线程绑定的特点去
    // 拓展：强，弱，软，虚引用
    public static void threadLocalCheck() {
        if (ContextHolder.get().contextTag.equals("user")) {
            try {
                Context context = new Context();
                context.contextId = UUID.randomUUID();
                context.contextContent = "userinfo";
                ContextHolder.set(context);
            } finally {
                ContextHolder.clear(); // 需要手动finally 调用threadLocal.remove()方法 清除内存 防止线程一直存活保留该引用 无法被gc
            }
        }
    }
}

// 举个例子，我们需要一个Context对象来保存当前线程的共享的上下文对象
// 这里可以举一反三，可以在很多切面去做这个Holder类，比如当前线程的链路跟踪、会话信息
class Context {
    UUID contextId;
    String contextTag;
    String contextContent;
}

