package a00_JavaBasics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chenyu Liu
 * @since 3/7/25 Friday
 **/

public class CollectionBasic {

    // primitive types
    void primitiveTypes() {
        // 整数类型
        byte byteValue = 10; // 1 字节
        short shortValue = 15; // 2 字节
        int intValue = 20; // 4 字节
        long longValue = 25L; // 8 字节 注意要在后面加L或l
        // 浮点类型（带小数部分）
        float floatValue = 3.14F; // 4 字节 注意要加f或者F
        double doubleValue = 3.1552; // 8 字节
        // 字符类型
        char charValue = 'd'; // 2 字节
        // 布尔类型
        boolean booleanValue = true;
    }

    // List
    void list() {
        // ArrayList基于动态数组实现，内存中连续
        // Default Capacity = 10
        // 容量不足会扩容1.5倍 oldCapacity + (oldCapacity >> 1)
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(3); // 插入删除元素效率 O(n)
        Integer integer = arrayList.get(0); // 访问效率O(1)，因为顺序存储，知道头指针便知道任意index的元素位置

        // LinkedList基于双向链表Deque实现
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(4); // 插入删除元素效率 O(1)
        Integer integer1 = linkedList.get(0); // 访问效率 O(n)
    }

    // Set考的少，因为内部都是用Map实现的
    void set() {
        HashSet<Integer> hashSet = new HashSet<>();
        // TODO: SortedSet
        // TODO: NavigableSet
        TreeSet<Integer> treeSet = new TreeSet<>();
    }

    // Queue
    void queue(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
    }

    // Map
    void map() {
        // 底层实现为数组
        HashMap<String, Integer> hashMap = new HashMap<>();

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        Hashtable<String,Integer> hashtable = new Hashtable<>();

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        // TODO: SortedMap
        // TODO: NavigableMap
    }
}

class HashMapBasic{
    /**
     * 插入或查找时会调用 hash 方法，如果key==null返回0，否则返回(h = key.hashCode()) ^ (h >>> 16)
     * 注意public native int hashCode() 该方法被native关键字修饰 --> 会存于本地方法栈，线程私有区域（回忆JVM内存模型）
     * h >>> 16 无符号右移 16位，因为hashCode返回int 4字节 32位
     * putVal用 (n-1) & hash , n过于小的时候 n-1 二进制高位都是0 ，会增加 hash conflict概率
     * (n - 1) & hash 将 n - 1 与 hash 进行按位与运算，相当于取 hash 的低几位作为数组的索引
     * n = 16, (n-1) = 15 = 0000 0000 0000 0000 0000 0000 0000 1111
     * DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16 初始化默认的n是16，bucket size就是16
     * 能这么算的前提是n必须是2的幂，这样能保证n-1低位都是1
     * 按位与&更快，取模%慢，算出来的值是桶的索引，会把数据加到该索引上链表后面（jdk1.8后是尾插），即尾指针的next，避免链表成环
     */

    /**
     * 桶扩容会发生在初始化以及桶长度小于最小树化容量因子（默认64）
     * tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY
     * 此时会调用resize将 桶数组变成2倍长
     * 如果桶长度大鱼64且链表长度大于8，则会触发treeifyBin
     * 红黑树是一种自平衡的二叉搜索树，其查找、插入和删除操作的时间复杂度为O(log n)，可以有效提高性能, 否则链表过长查询效率很低
     * 红黑树中节点数量少于UNTREEIFY_THRESHOLD 默认=6 时会转换为链表
     * 红黑树又涉及到BST binary search tree
     * 任意节点的左子树都比它小，右子树都比他大，且左右子树也是二叉搜索树
     * BST 在插入的所有数据都是排好序的情况下，会变成线性的树，读写复杂度会变成O(n)，性能退化
     * 优化得到AVL Tree
     * 任意情况下左子树右子树深度差<=1，最差情况读写复杂度O(logn)
     * 但AVL树在插入删除频繁的操作中，需要大量左旋与右旋操作
     * 所以红黑树是相对AVL不那么严格的平衡树，介于BST与AVL Tree之间
     */


}
class RedBlackTree{
    // 红黑树用于HashMap TreeMap TreeSet
    // epoll监控socket的文件描述符
    // Nginx Timer
    /**
     * Red-Black Tree 红黑树
     * All node Red or Black 所有节点非红即黑
     * root node = Black node 根节点必须为黑节点
     * leaf null node = Black node 叶子NIL节点视为节点（潜在说明整树的一半以上节点为黑节点）
     * Child of Red node must be Black node 红节点子树必须为黑节点（或者不能有连续红节点）
     * 任意节点到叶子NIL节点经过的黑色节点数相同（极限情况是 节点数相同-全黑 到 节点数差一倍-红黑相间）
     * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
     */
}