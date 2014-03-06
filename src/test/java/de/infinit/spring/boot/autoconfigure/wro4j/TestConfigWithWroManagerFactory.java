package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.isdc.wro.manager.factory.WroManagerFactory;

@Configuration
public class TestConfigWithWroManagerFactory {

    @Bean
    WroManagerFactory testWroManagerFactory() {
        return new TestWroManagerFactory();
    }

}
