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
#### 配置
<configuration>元素，包含零个或多个<appender>元素，后跟零个或多个<logger>元素，后跟至多一个<root>元素

![](img/config.png)
1. `<logger>`元素只需要一个强制性的name属性、一个可选的level属性和一个可选的additivity属性，接受值true或false。<logger>元素可含有零个或多个`<appender-ref>`元素
2. `<root>`元素配置根记录器。它支持单个属性，即level属性。它不允许任何其他属性，因为additivity标志不适用于根记录器。<root>元素可以包含零个或多个`<appender-ref>`元素
3. `<appender>`元素，它有两个必需的属性name和class。该name属性指定，而该附加目的地的名称类属性指定的appender类实例化的全名。`<appender>`元素可含有零个或一个`<layout>`元素，零个或多个`<encoder>`元素以及零层或更多`<filter>`的元素。除了这三个公共元素之外，`<appender>`元素还可以包含与appender类的JavaBean属性对应的任意数量的元素。

![](img/appender.png)
> 默认情况下，appender 是累积的：记录器将记录附加到它自己的附加器（如果有的话）以及附加到它的祖先的所有附加器。因此，将同一个 appender 附加到多个记录器将导致记录输出重复。
```
<configuration>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>

  <logger name="chapters.configuration">
    <appender-ref ref="STDOUT" />
  </logger>

  <root level="debug">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```