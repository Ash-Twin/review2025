package a00_JavaBasics;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

public class ClassLoaderBasic {
    /**
     * BootstrapClassLoader(加载核心库JRE/lib/rt.jar)
     * ExtensionClassLoader(加载扩展库JRE/lib/ext/*.jar) --> JDK 9之后变成PlatformClassLoader
     * APP ClassLoader(加载classpath/*.jar)
     * Custom ClassLoader(自定义的加载器）
     */
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderBasic.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
        }
    }
    /**
     * Parent Delegation 双亲委派（我其实倾向于父级委派，因为没体现双亲）
     * 尝试父级类加载器加载，父级也会往父级的父级请求加载
     * 如果可以则加载到元空间，如果不行则子类完成（此时则会打破双亲委派)
     * 避免重复加载，以及避免自定义核心库重名类，因为一直会是父级app>platform>bootstrap来加载
     * 破坏双亲委派：1. 热部署
     * 2. Service Provider Interface SPI：接口本身由bootstrap platform加载，但其实现由app 加载
     */
    public void jdbc(String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password); // DriverManager是bootstrapClassLoader
        //但 各个数据库有自己的Driver实现，所以要用appClassLoader来加载
    }
}
