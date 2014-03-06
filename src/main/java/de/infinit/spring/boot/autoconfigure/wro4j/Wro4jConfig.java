package de.infinit.spring.boot.autoconfigure.wro4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.resource.locator.factory.ConfigurableLocatorFactory;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.support.hash.ConfigurableHashStrategy;
import ro.isdc.wro.model.resource.support.naming.ConfigurableNamingStrategy;

import java.util.Properties;

@Configuration
public class Wro4jConfig {

    @Value("${wro4j.disablecache:false}")
    boolean disableCache;

    @Value("${wro4j.debug:false}")
    boolean debug;

    @Value("${wro4j.cacheupdateperiod:0}")
    int cacheUpdatePeriod;

    @Value("${wro4j.resourcewatcherupdateperiod:5}")
    int resourceWatcherUpdatePeriod;

    @Value("${wro4j.urlpattern:/assets/*}")
    String urlPattern;

    @Value("${wro4j.urilocators:servletContext,classpath,uri}")
    String uriLocators;

    @Value("${wro4j.preprocessors:cssUrlRewriting,cssImport,semicolonAppender,lessCss}")
    String preProcessors;

    @Value("${wro4j.postprocessors:cssVariables,cssMinJawr,jsMin}")
    String postProcessors;

    @Value("${wro4j.groovyresourcename:wro.groovy}")
    String groovyResourceName;

    @Value("${wro4j.hashstrategy:MD5}")
    String hashStrategy;

    @Value("${wro4j.namingstrategy:hashEncoder-CRC32}")
    String namingStrategy;

    @Bean
    ConfigurableWroFilter wroFilter(WroManagerFactory wroManagerFactory) {
        ConfigurableWroFilter wroFilter = new ConfigurableWroFilter();
        wroFilter.setProperties(wroFilterProperties());
        wroFilter.setWroManagerFactory(wroManagerFactory);
        return wroFilter;
    }

    Properties wroFilterProperties() {
        Properties properties = new Properties();
        properties.put(ConfigConstants.debug.name(), String.valueOf(debug));
        properties.put(ConfigConstants.disableCache.name(), String.valueOf(disableCache));
        properties.put(ConfigConstants.cacheUpdatePeriod, String.valueOf(cacheUpdatePeriod));
        properties.put(ConfigConstants.resourceWatcherUpdatePeriod, String.valueOf(resourceWatcherUpdatePeriod));
        return properties;
    }

    @Bean
    FilterRegistrationBean wro4jFilterRegistration(ConfigurableWroFilter wroFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(wroFilter);
        filterRegistrationBean.addUrlPatterns(urlPattern);
        return filterRegistrationBean;
    }

    @ConditionalOnClass(groovy.lang.GroovyObject.class)
    @ConditionalOnMissingBean(WroManagerFactory.class)
    @Bean
    WroManagerFactory groovyWroManagerFactory() {
        return new GroovyWroManagerFactory(groovyResourceName, wroManagerFactoryProperties());
    }

    @ConditionalOnMissingBean(WroManagerFactory.class)
    @Bean
    WroManagerFactory fallbackWroManagerFactory() {
        return new SimpleWroManagerFactory(wroManagerFactoryProperties());
    }

    Properties wroManagerFactoryProperties() {
        Properties properties = new Properties();
        properties.put(ConfigurableLocatorFactory.PARAM_URI_LOCATORS, uriLocators);
        properties.put(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS, preProcessors);
        properties.put(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS, postProcessors);
        properties.put(ConfigurableNamingStrategy.KEY, namingStrategy);
        properties.put(ConfigurableHashStrategy.KEY, hashStrategy);
        return properties;
    }

}
