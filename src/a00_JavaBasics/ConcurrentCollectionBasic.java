package a00_JavaBasics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Chenyu Liu
 * @since 3/8/25 Saturday
 **/

public class ConcurrentCollectionBasic {

    // 写volatile时会汇编层面产生Lock前缀指令，将处理器缓存写回系统内存
    // 读volatile时JMM会把该线程工作内存的volatile变量缓存设为无效，从主内存读
    // volatile只解决可见性，原子性与一致性没办法解决
    private volatile int volatileValue = 0;



    // 基于CAS compare and swap的原子操作完成
    // java.util.concurrent.atomic包下的原子类
    // 基本都是用volatile关键字以及native方法（unsafe java)实现
    private ConcurrentHashMap<String, String> map  = new ConcurrentHashMap<>();
    private ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();
    //还有一些BlockingCollection
}
