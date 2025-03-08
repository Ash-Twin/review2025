package main.java.b000_algorithms.a09_MinStack;

import java.util.Stack;

/**
 * @author Chenyu Liu
 * @since 3/4/25 Tuesday
 **/

public class Solution {
    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * <p>
     * Implement the MinStack class:
     * <p>
     * MinStack() initializes the stack object.
     * void push(int val) pushes the element val onto the stack.
     * void pop() removes the element on the top of the stack.
     * int top() gets the top element of the stack.
     * int getMin() retrieves the minimum element in the stack.
     * You must implement a solution with O(1) time complexity for each function.
     */
    class MinStack {

        private Stack<Integer> dataStack; // 栈基本实现
        private Stack<Integer> minElement; // 只压入最小数，每次dataStack pop push都同时操作，只不过dataStack压入弹出数据时，minElement只压入最小数（判断当前压入的数与栈顶）

        public MinStack() {
            this.dataStack = new Stack<>();
            this.minElement = new Stack<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minElement.isEmpty()) {
                minElement.push(val);
            } else {
                minElement.push(minElement.peek() > val ? val : minElement.peek());
            }
        }

        public void pop() {
            dataStack.pop();
            minElement.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minElement.peek();
        }
    }
}
