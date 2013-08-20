Guice Config
============
Reading externalized primitive configurations in Guice apps.

#### Usage

Load configs:
```java
// Java Preferences
Module conf = PrefesModule.system("/path/to/prefs");

// JNDI
Module conf = new JndiModule();

// Properties (file)
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
