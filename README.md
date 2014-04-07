# spring-boot-autoconfigure-wro4j
A spring-boot-autoconfigure library that simplifies the usage of the [wro4j](https://code.google.com/p/wro4j/) library. You only have to add this library a depedency to your spring-boot-autoconfigure enabled project and a wro4j filter will be registered for you. This library currently depends on the wro4j-extension library which includes several frameworks to handle and process web resources. If you want to exclude some frameworks you currently have to do it yourself using your build systems way of excluding transitive dependencies.

## Usage
If you are having groovy on your classpath the library expects a [wro.groovy](https://code.google.com/p/wro4j/wiki/GroovyWroModel) file in your classpath as the wro descriptor. If groovy is not present it falls back to the [xml based approach](https://code.google.com/p/wro4j/wiki/WroFileFormat). Please visit the [website](https://code.google.com/p/wro4j/) of wro4j for further documentation.

[ ![Download](https://api.bintray.com/packages/sbuettner/maven/spring-boot-autoconfigure-wro4j/images/download.png) ](https://bintray.com/sbuettner/maven/spring-boot-autoconfigure-wro4j/_latestVersion)

### Gradle Integration
Add the following repository and dependency to your ``build.gradle`` build file:

```
repositories {
  // ...
  maven { url 'http://dl.bintray.com/sbuettner/maven' }
}

dependencies {
  ...
  
  // This project
  compile(group: 'de.infinit', name: 'spring-boot-autoconfigure-wro4j', version: 'LATEST_VERSION')
  
  // WRO Extensions - exclude the libraries you dont need support for.
  compile("ro.isdc.wro4j:wro4j-extensions:${wro4jVersion}")
  
}
```
You can find the latest binary version of this library here: https://bintray.com/sbuettner/maven/spring-boot-autoconfigure-wro4j/view

Have a look at https://code.google.com/p/wro4j/wiki/GettingStarted for further wro4j dependency related information.
