package de.infinit.spring.boot.autoconfigure.wro4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationWithWroManagerFactory.class, loader = SpringApplicationContextLoader.class)
public class TestApplicationWithWroManagerFactoryTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull("The TestWroManagerFactory should be present", applicationContext.getBean(TestWroManagerFactory.class));
    }

}
