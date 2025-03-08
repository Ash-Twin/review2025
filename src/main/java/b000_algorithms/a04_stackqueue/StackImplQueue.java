package main.java.b000_algorithms.a04_stackqueue;

import java.util.Stack;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class StackImplQueue {

    public Stack<Integer> in;
    public Stack<Integer> out;

    public StackImplQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * 把数据从in中全部倒入out
     * 此时out必须为空，in必须不为空
     * in必须全部倒入out，倒完后in为空
     */
    public void inToOut(){
        if(out.empty()){
            while (!in.empty()){
                out.push(in.pop());
            }
        }
    }

    /**
     * in空了才能push，保证顺序是对的
     * @param x
     */
    public void push(int x) {
        in.push(x);
    }

    /**
     * out空了才可以pop，保证顺序是对的
     * 因为元素是先进in，再进out，再弹出
     * 如果out中有元素，那么这次没pop干净的会导致下次顺序出问题
     */
    public int pop() {
        inToOut();
        return out.pop();
    }

    public int peek() {
        inToOut();
        return out.peek();
    }

    public boolean empty() {
        return in.empty() && out.empty();
    }
}
