Guice Config
============
Reading Guice configs from JNDI, Java Preferences, etc.

#### Usage

Load configs:
```java
// Java Preferences
Module conf = PrefesModule.system("/path/to/prefs");

// JNDI
Module conf = new JndiModule();

// Properties
Module conf = new PropsModule(properties);
```

Install them:
```java
protected void configure() {
    install(conf);
    ...
}
```

Or push them:
```java
Modules.override(mainModule).with(config);
```

#### Maven

```xml
<dependency>
    <groupId>me.shakiba.guice-config</groupId>
    <artifactId>guice-config</artifactId>
    <version>0.0.4</version>
</dependency>
```
