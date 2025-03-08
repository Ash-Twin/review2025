package main.java.a00_JavaBasics;

// 我们会用一个ContextHolder来保存更新与获取
public class ContextHolder {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<>();

    public static void set(Context context) {
        threadLocal.set(context);
    }

    public static Context get() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
