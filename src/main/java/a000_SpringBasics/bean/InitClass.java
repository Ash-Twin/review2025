package a000_SpringBasics.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@Configuration
public class InitClass {

    @Bean()
    public BeanBasics beanBasicsBean(){
        return new BeanBasics();
    }

}
