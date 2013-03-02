Guice Config
============
Reading Guice configs from JNDI, Java Preferences, etc.

#### Usage

```java
// Java Preferences
Modules.override(module).with(PrefesModule.system("/path/to/prefs"));

// JNDI
Modules.override(module).with(new JndiModule());
```

It can be used along with [preferd](https://github.com/shakiba/preferd) to manage configurations injected by guice.

#### Maven

```xml
<dependency>
    <groupId>me.shakiba.guice-config</groupId>
    <artifactId>guice-config</artifactId>
    <version>0.0.2-SNAPSHOT</version>
</dependency>
```
