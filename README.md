# spring-boot-autoconfigure-wro4j
A spring-boot-autoconfigure library that simplifies the usage of the wro4j library at https://code.google.com/p/wro4j/

To use this autoconfigure library simply add the following lines to your gradle file. If you are using maven please use the appropriate dependency and repository configuration format.

## Gradle
Add the following repository and dependency to your ``build.gradle`` build file:

```
repositories {
  // ...
  maven { url 'http://dl.bintray.com/sbuettner/maven' }
}

dependencies {
  // ...
  compile(group: 'de.infinit', name: 'spring-boot-autoconfigure-wro4j', version: 'LATEST_VERSION')
}
```
You can find the latest binary version here: https://bintray.com/sbuettner/maven/spring-boot-autoconfigure-wro4j/view
