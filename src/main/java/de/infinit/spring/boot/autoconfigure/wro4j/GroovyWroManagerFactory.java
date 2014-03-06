package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.core.io.ClassPathResource;
import ro.isdc.wro.extensions.model.factory.GroovyModelFactory;
import ro.isdc.wro.model.factory.WroModelFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Allows the usage of groovy files as wro4j descriptors.
 */
public class GroovyWroManagerFactory extends SimpleWroManagerFactory {

    private final String resourceName;

    public GroovyWroManagerFactory(String resourceName, Properties configProperties) {
        super(configProperties);
        this.resourceName = resourceName;
    }

    @Override
    protected WroModelFactory newModelFactory() {
        return new GroovyModelFactory() {
            @Override
            protected InputStream getModelResourceAsStream() throws IOException {
                return new ClassPathResource(resourceName).getInputStream();
            }
        };
    }

}
