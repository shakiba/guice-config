Guice Config
============
Reading Guice configs from JNDI, Java Preferences, etc.

#### Usage

```java
// Java Preferences
Modules.override(module).with(PrefesModule.system("/path/to/prefs"));

// JNDI
Modules.override(module).with(new JndiModule());

// Properties
Modules.override(module).with(new PropsModule(properties));

```

#### Maven

```xml
<dependency>
    <groupId>me.shakiba.guice-config</groupId>
    <artifactId>guice-config</artifactId>
    <version>0.0.2</version>
</dependency>
```
