package main.java.a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

public class SelfIncrement {
    /**
     * 对于 JVM 而言，它对自增运算的处理，是会先定义一个临时变量来接收 i 的值，然后进行自增运算，最后又将临时变量赋给了值为 2 的 i，所以最后的结果为 1。
     */
    static void func0(){
        int i  = 1;
        i = i++;
        System.out.println(i);
    }

    /**
     * 等效于
     */
    static void func1(){
        int i = 1;
        int temp = i;
        i++;
        i = temp;
        System.out.println(i);
    }
    static void func2(){
        int count = 0;
        for(int i = 0;i < 100;i++)
        {
            count = count++;
        }
        System.out.println("count = "+count);
    }
    public static void main(String[] args) {
       func0(); // 1
       func1(); // 1
       func2(); // 0
    }
}
