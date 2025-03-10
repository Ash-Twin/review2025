package a000_SpringBasics.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

public class BeanBasics implements InitializingBean, DisposableBean {

    public String getName() {
        System.out.println("getName:" + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("setName:" + name);
        this.name = name;
    }

    @Value("${spring.lifecycle.custom.bean.name}")
    private String name;

    public BeanBasics() {}

    @PostConstruct()
    public void postConstruct(){
        System.out.println("postConstruct:"+name);
    }


    @PreDestroy
    public void preDestroy(){
        System.out.println("preDestroy:"+name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroyed:"+name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("properties set:"+name);
    }
}
