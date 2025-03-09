package a00_JavaBasics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

public class ThreadPoolBasic {

    private ExecutorService cachedPool = Executors.newCachedThreadPool();
    // ThreadPoolExecutor(
    //   corePoolSize = 0
    //   maxPoolSize = Integer.MaxValue
    //   keepAliveTime = 60
    //   workQueue = new SynchronizedQueue<>()
    // )

    private ExecutorService fixedPool = Executors.newFixedThreadPool(10);
    // ThreadPoolExecutor(
    //   corePoolSize = 10
    //   maxPoolSize = 10
    //   keepAliveTime = 0
    //   workQueue = new LinkedBlockingQueue<>()
    // )

    /**
     * Question: corePool的线程对象不被删除但是执行queue中的新runnable，如何做到？
     */
}
