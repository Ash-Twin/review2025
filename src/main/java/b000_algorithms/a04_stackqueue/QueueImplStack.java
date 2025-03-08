package main.java.b000_algorithms.a04_stackqueue;

import java.util.ArrayDeque;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class QueueImplStack {
    /**
     * 使用队列实现栈
     * 一个in queue一个out queue，反转后使数据变成LIFO
     * inQueue往outQueue的队尾塞
     * 条件：out stack必须空才可以继续
     */

    public ArrayDeque<Integer> inQueue;
    public ArrayDeque<Integer> outQueue;

    public QueueImplStack() {
        inQueue = new ArrayDeque<>();
        outQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        inQueue.add(x);
    }

    public int pop() {
        return inQueue.removeLast();
    }

    public int top() {
        return inQueue.getLast();
    }

    public boolean empty() {
        return inQueue.isEmpty();
    }
}
