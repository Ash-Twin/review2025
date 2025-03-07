package a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

// 定义一个包含 static 成员的类
class StaticExample {
    // 静态变量，被所有实例共享
    public static int staticVariable = 0;
    // 实例变量，每个实例都有自己的一份
    public int instanceVariable = 0;

    // 静态代码块，在类加载时执行，且只执行一次
    static {
        System.out.println("静态代码块执行，staticVariable 初始化为 10");
        staticVariable = 10;
    }

    // 实例代码块，在创建对象时执行，每次创建对象都会执行
    {
        System.out.println("实例代码块执行，instanceVariable 初始化为 20");
        instanceVariable = 20;
    }

    // 构造方法
    public StaticExample() {
        System.out.println("构造方法执行");
    }

    // 静态方法，可直接通过类名调用
    public static void staticMethod() {
        System.out.println("静态方法被调用，staticVariable 的值为: " + staticVariable);
        // 静态方法中不能直接访问实例变量和实例方法
        // System.out.println(instanceVariable); // 编译错误
    }

    // 实例方法
    public void instanceMethod() {
        System.out.println("实例方法被调用，instanceVariable 的值为: " + instanceVariable);
        // 实例方法中可以访问静态变量和静态方法
        System.out.println("在实例方法中访问 staticVariable: " + staticVariable);
        staticMethod();
    }

    // 静态内部类
    static class StaticInnerClass {
        public void printMessage() {
            System.out.println("静态内部类的方法被调用");
        }
    }
}

public class StaticBasic {
    public static void main(String[] args) {
        // 直接通过类名访问静态变量和静态方法
        System.out.println("通过类名访问 staticVariable: " + StaticExample.staticVariable);
        StaticExample.staticMethod();

        // 创建对象
        StaticExample obj1 = new StaticExample();
        System.out.println("obj1 的 instanceVariable: " + obj1.instanceVariable);
        System.out.println("obj1 的 staticVariable: " + obj1.staticVariable);

        // 修改静态变量的值
        StaticExample.staticVariable = 30;
        System.out.println("修改 staticVariable 后，obj1 的 staticVariable: " + obj1.staticVariable);

        // 创建另一个对象
        StaticExample obj2 = new StaticExample();
        System.out.println("obj2 的 instanceVariable: " + obj2.instanceVariable);
        System.out.println("obj2 的 staticVariable: " + obj2.staticVariable);

        // 调用实例方法
        obj1.instanceMethod();

        // 创建静态内部类的实例并调用方法
        StaticExample.StaticInnerClass innerObj = new StaticExample.StaticInnerClass();
        innerObj.printMessage();
    }
}
