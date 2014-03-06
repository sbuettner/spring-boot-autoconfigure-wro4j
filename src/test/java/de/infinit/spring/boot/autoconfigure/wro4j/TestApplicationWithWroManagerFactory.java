package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import ro.isdc.wro.manager.factory.WroManagerFactory;

@EnableAutoConfiguration
public class TestApplicationWithWroManagerFactory {

    @Bean
    WroManagerFactory testWroManagerFactory() {
        return new TestWroManagerFactory();
    }

}
