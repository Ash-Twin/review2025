package a08_CircularQueueImpl;

/**
 * @author Chenyu Liu
 * @since 3/4/25 Tuesday
 **/

public class Solution {
    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
    class MyCircularQueue {

        public int[] array;
        public int head = 0, tail = 0, size, limit;

        public MyCircularQueue(int k) {
            size = 0;
            limit = k;
            array = new int[k];
        }

        public boolean enQueue(int value) {
            if (size < limit) {
                array[tail] = value;
                tail = tail == limit - 1 ? 0 : tail + 1;
                size++;
                return true;
            } else {
                return false;
            }
        }

        public boolean deQueue() {
            if (size > 0) {
                head = head == limit - 1 ? 0 : head + 1;
                size--;
                return true;
            } else {
                return false;
            }
        }

        public int Front() {
            if (isEmpty()) return -1;
            else return array[head];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            else return tail == 0 ? array[limit - 1] : array[tail - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }


    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

    class MyCircularDeque {

        public int[] array;
        public int head = 0, tail, size, limit;

        public MyCircularDeque(int k) {
            limit = k;
            size = 0;
            tail = limit -1; //注意这里的初始化和circularqueue的区别，tail现在直接在初始化数组的尾。
            array = new int[limit];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            } else {
                head = (head - 1 + limit) % limit;
                array[head] = value;
                size++;
                return true;
            }
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            } else {
                tail = (tail + 1) % limit;
                array[tail] = value;
                size++;
                return true;
            }

        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            } else {
                head = (head + 1) % limit;
                size--;
                return true;
            }
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            } else {
                tail = (tail - 1 + limit) % limit;
                size--;
                return true;
            }
        }

        public int getFront() {
            if (isEmpty()) return -1;
            else return array[head];
        }

        public int getRear() {
            if (isEmpty()) return -1;
            else return array[tail];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }


}
