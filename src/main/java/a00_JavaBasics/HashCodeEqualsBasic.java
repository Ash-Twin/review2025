package main.java.a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/8/25 Saturday
 **/

public class HashCodeEqualsBasic {
    // hashCode 与 equals没有关系，因为我们可以Override它们
    // 规范1：hashCode 与 equals 返回值应该是稳定的，不能有随机性
    // 规范2：两对象 == 返回true，则equals也返回true
    // 规范3：两对象 equals返回true，则hashCode也应该相等
    // 规范4：默认对象的hashCode永远大于0，对象头有31位存储hashCode，符号位没有用
    // 规范5：默认对象的hashCode返回值不会是对象地址

    public static void main(String[] args) {

        System.out.println("a" == "a"); // true
        // 字符串常量会存储在字符串常量池，字符串常量池位于堆中
        // 第一次出现"a"的时候如果常量池没有，则创建
        // 所以 "a" == "a" 右侧的 "a" 实际上就是左侧"a"字符串对象的引用
        // 考点1：String是对象
        // 考点2：对象创建位置

        System.out.println("---");

        System.out.println("a" == new String("a")); //false
        System.out.println("a".equals(new String("a")));
        // new关键字会强制在heap中创建新的"a"字符串对象
        // 两个对象引用不一样，但用equals比较值则返回true
        // 考点：new关键字

        System.out.println("---");

        Integer x1 = 1, y1 = 1;
        System.out.println(x1 == y1); // true
        Integer x2 = 128, y2 = 128;
        System.out.println(x2 == y2); // false (out of cached auto boxing range)
        System.out.println(x2.equals(y2));
        // 考点：Integer类缓存机制 [-128, 127] 之间的整数 会auto boxing缓存起来
        // 相当于已经创建好的 Integer对象 直接被用
        // 所以此时x1, y1实际上是已经存在的对象
        // 而x2，y2是两个新对象，只不过值相等但是地址不一样
        // 应该用equals比较值

        System.out.println("---");

        System.out.println(new Integer(1) == new Integer(1));
        System.out.println(new Integer(1).equals(new Integer(1)));
        // new会强制创建新对象，不论Integer缓存机制
        // 应该用equals比较值
    }
}
