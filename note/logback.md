#### logback查找配置的顺序
1. Logback tries to find a file called logback-test.xml in the classpath.
2. If no such file is found, it checks for the file logback.xml in the classpath..
3. If no such file is found, service-provider loading facility (introduced in JDK 1.6) is used to resolve the implementation of com.qos.logback.classic.spi.Configurator interface by looking up the file META-INF\services\ch.qos.logback.classic.spi.Configurator in the class path. Its contents should specify the fully qualified class name of the desired Configurator implementation.
4. If none of the above succeeds, logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.
##### OnConsoleStatusListener

```
<configuration>
    <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener" />
</configuration>

setting debug="true" is strictly equivalent to installing an OnConsoleStatusListener
<configuration debug="true">
</configuration>
```

> java -Dlogback.statusListenerClass =ch.qos.logback.core.status.OnConsoleStatusListener