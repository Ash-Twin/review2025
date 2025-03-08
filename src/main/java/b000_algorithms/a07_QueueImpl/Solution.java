package b000_algorithms.a07_QueueImpl;

/**
 * Queue impl using list
 *
 * @author Chenyu Liu
 * @since 3/4/25 Tuesday
 **/

public class Solution {

    class QueueImplUsingList {
        /**
         * 其实就是利用链表指针，保存头指针与尾指针
         */

    }

    class QueueImplUsingArray {
        /**
         * 题目告诉你加入操作的总次数上限为N
         * 此时可以用N长度的Array来实现Queue
         * pop与add都是O（1）常数时间更快
         */
        public int[] array;
        public int head;
        public int tail;

        QueueImplUsingArray() {
            this.array = new int[100]; // size = 100
            this.head = 0;
            this.tail = 0;
        }

        boolean isEmpty() {
            return head == tail; // 如果头尾指针相等则队列空，调用任何方法前要判断非空
        }

        int head() {
            return this.array[head];
        }

        int tail() {
            return this.array[tail - 1]; //因为tail初始化是0，添加数据是会添加到tail的位置，然后tail自增，所以当前的尾部是在tail - 1位置
        }

        void offer(int x) {
            this.array[tail++] = x;
        }

        int poll() {
            return this.array[head++];
        }
    }


    public static void main(String[] args) {


    }
}
