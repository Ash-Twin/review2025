package main.java.a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/5/25 Wednesday
 **/

public class JvmBasic {

    static int i = 10; // static 关键字修饰的变量会存储在方法区或元空间

    public static void main(String[] args) {
        int a = 10;
        JvmBasic jvmBasic = new JvmBasic();
        jvmBasic.func1(a); // func1函数调用完毕，线程执行结束，需要释放对应内存，因为栈结构先进后出，先声明的a后声名的b，所以先栈会先释放b后释放a
        jvmBasic.func2(a); // func2同理
        // 注意此时清空的都是栈空间，但是堆上的对象都没有清理，此时便需要引入GC
        /**
         * GC Root:堆上被 栈、本地方法栈、方法区/元空间 直接或间接引用的对象
         * 标记-清理方法(mark&sweep): 在对象上打标，再次扫描全部删除标记对象，缺点是会产生内存碎片——删除的对象与对象之间的碎片空间不能被很好的利用，如果太小则大对象需要另找空间
         * 标记-整理方法：标记-清理后把之间有碎片空间的对象挪到挨着的位置，但开销太大了，意味着堆上所有对象都要动
         * 复制方法：给堆分区，对象创建于1区，2区是空，1区未标记的对象在gc时复制到2区，反之回到1区。缺点是需要2倍内存来完成操作
         * -------------
         * 实际GC更加合理
         *                 Young Gen          Old Gen
         *          +---------------------+-----------------+
         *          ｜       Eden         ｜   大对象/老对象  ｜
         *          +---------------------+                 ｜
         *          ｜    s0   ｜   s1     ｜                ｜
         *          +---------------------+-----------------+
         *  Young GC 的Eden会比s0 s1大不少，因为对象基本上存活时间很短，创建于eden幸存下来复制到s0的很少
         *  s0 与 s1交替使用，每次GC会删除Eden与对应的s区
         *  每次对象在gc存活下来时age会加1，age >= 6 的对象会被移到old gen，不再参与gc
         *  old gen也会存大对象，比如超长数组，大对象gc时复制的开销太大
         *  大对象 -XX:PretenureSizeThreshold 但只对Serial与ParNew生效 有些可能会设置成5MB
         *  老对象 -XX:MaxTenuringThreshold 不同GC默认不一样
         *  old gen快满了也会触发gc，old gen gc同时会伴随young gc，此时便是full gc
         *  gc 会触发stop the world stw，java程序会暂停等待gc完成
         *  ————————————
         *  G1GC 会把heap分成很多个region，虽然eden survivor old还是在，但是分别化为一个一个的小区域
         *  每一个region 1M-32M
         *  如果 0.5region <= 对象 <= 1region，直接进old region
         *  如果 对象 > 1region 申请多个H区(Humongous Region是特殊的Old Region)来存储
         *  Rset Remember set记录其他region引用当前region对象
         *  Cset 本次gc需清理的region集合
         *
         */
        System.out.println(a); // 此时程序会打印20与10，尽管修改了函数里的a，但这里打印的是main函数的变量a，func1函数的内存空间已经被释放了，也因为func1的参数是值传递
    }

    private void func1(int a) {  // main 函数调用func，func的临时变量a计入线程调用栈
        int b = 10;     // b 被初始化，b也被计入线程栈
        System.out.println(a + b);
        a = 11; // 改变线程栈里变量a的值为11，现在线程栈里存在func1函数对应的变量a = 11， b = 10
    } // 此时func1在main函数中调用完毕

    private void func2(int a){
        int b = 10;
        Person p = new Person(); // 注意对象存储在栈上的是其引用地址，会在heap上开辟空间，地址占4个字节
        p.id = 1; // 会寻址到堆上的Person对象，为其赋值 1
        p.name = "person"; // String是对象类型不是值类型，所以此时堆上的Person对象的name也存储的是地址，会再在堆上创建String对象，char数组存储p,e,r,s,o,n
        System.out.println(a + b);
    }
    private void func3(Person p){
        p.id = 32;
        p.name = "modified";
    } // 此时对p的修改会直接影响，因为p存储在堆上，其引用总会寻址到对象，如果是值类型，每次都会在栈上开辟空间存储局部变量
}

class Person{
    int id;
    String name;
}