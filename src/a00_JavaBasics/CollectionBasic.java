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
        TreeSet<Integer> treeSet = new TreeSet<>();

    }

    // Map
    void map() {
        // 底层实现为数组
        HashMap<String, Integer> hashMap = new HashMap<>();

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        Hashtable<String,Integer> hashtable = new Hashtable<>();

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
    }
}
