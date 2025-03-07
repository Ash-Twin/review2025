package a00_JavaBasics;

/**
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

public class LogicExpression {

    /**
     * &&在左侧表达式为false就会直接失败
     *
     * @param username
     * @return
     */
    boolean and1(String username) {
        return username != null && username.equals("");
    }

    /**
     * 单独的&则会判断左右两个表达式
     *
     * @param username
     * @return
     */
    boolean and2(String username) {
        return username != null & username.equals("");
    }

    boolean or1(String username) {
        return username != null || username.equals("");
    }

    boolean or2(String username) {
        return username != null | username.equals("");
    }

    /**
     * & 和 | 不具备短路特性，无论左边表达式的结果如何，右边表达式都会被计算；而 && 和 || 具有短路特性，根据左边表达式的结果可能会跳过右边表达式的计算。
     * 在实际编程中，通常建议优先使用 && 和 ||，因为它们的短路特性可以提高程序的执行效率，避免不必要的计算。
     */
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        boolean result1 = (a > 3) & (b < 20); // 两个表达式都为 true，结果为 true
        boolean result2 = (a < 3) & (b++ < 20); // 左边表达式为 false，右边表达式仍会计算，b 的值变为 11
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
        System.out.println("b after &: " + b);

        // 使用 && 运算符
        b = 10; // 重置 b 的值
        boolean result3 = (a > 3) && (b < 20); // 两个表达式都为 true，结果为 true
        boolean result4 = (a < 3) && (b++ < 20); // 左边表达式为 false，右边表达式不会计算，b 的值仍为 10
        System.out.println("result3: " + result3);
        System.out.println("result4: " + result4);
        System.out.println("b after &&: " + b);
    }
}
