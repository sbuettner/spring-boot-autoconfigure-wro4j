package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(Wro4jConfig.class)
public class TestApplication {

}
