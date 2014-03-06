package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({TestConfigWithWroManagerFactory.class, Wro4jConfig.class})
public class TestApplicationWithWroManagerFactory {


}
