package de.infinit.spring.boot.autoconfigure.wro4j;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import java.util.Properties;

public class SimpleWroManagerFactory extends ConfigurableWroManagerFactory {

    private final Properties configProperties;

    public SimpleWroManagerFactory(Properties configProperties) {
        this.configProperties = configProperties;
    }

    @Override
    protected Properties newConfigProperties() {
        return configProperties;
    }

}
