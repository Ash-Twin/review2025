package a000_SpringBasics.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@SpringBootTest
public class BeanBasicsTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testBean(){
        context.getBean(BeanBasics.class);
    }

}
