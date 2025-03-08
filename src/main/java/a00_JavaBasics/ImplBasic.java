package main.java.a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

public class ImplBasic {
    /**
     * 多态通过动态绑定实现，Java 使用虚方法表存储方法指针，方法调用时根据对象实际类型从虚方法表查找具体实现。
     */
    class food{
        int price;
        String name;
    }
    class tomato extends food{
        String nutrition;
    }

    /**
     * java不支持多继承，但支持多实现
     */
    interface plant{
        String getCategory();
    }
    interface fruit{
        String getCapacity();
    }
    class cucumber extends food implements plant, fruit{

        @Override
        public String getCategory() {
            return null;
        }

        @Override
        public String getCapacity() {
            return null;
        }
    }
}
