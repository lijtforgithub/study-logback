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
java -Dlogback.configurationFile=/path/to/config.xml

![](img/config.png)
1. `<logger>`元素只需要一个强制性的name属性、一个可选的level属性和一个可选的additivity属性，接受值true或false。<logger>元素可含有零个或多个`<appender-ref>`元素
2. `<root>`元素配置根记录器。它支持单个属性，即level属性。它不允许任何其他属性，因为additivity标志不适用于根记录器。<root>元素可以包含零个或多个`<appender-ref>`元素
3. `<appender>`元素，它有两个必需的属性name和class。该name属性指定，而该附加目的地的名称类属性指定的appender类实例化的全名。`<appender>`元素可含有零个或一个`<layout>`元素，零个或多个`<encoder>`元素以及零层或更多`<filter>`的元素。除了这三个公共元素之外，`<appender>`元素还可以包含与appender类的JavaBean属性对应的任意数量的元素。

![](img/appender.png)
> 默认情况下，appender 是累积的：记录器将记录附加到它自己的附加器（如果有的话）以及附加到它的祖先的所有附加器。因此，将同一个 appender 附加到多个记录器将导致记录输出重复。additivity="false"可以覆盖默认行为
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
4. 上下文
```
<contextName>${APP_NAME}</contextName>
<pattern>%d %contextName [%t] %level %logger{36} - %msg%n</pattern>
```
5. 变量
```
<property name = "USER_HOME" value = "/home/logs" />
java -DUSER_HOME="/home/logs"

<property file="src/main/java/chapters/configuration/variables1.properties" />
// classpath
<property resource="resource1.properties" />
```
- 本地范围具有本地范围的属性存在于配置文件中的定义点，直到所述配置文件的解释/执行结束。因此，每次解析和执行配置文件时，都会重新定义局部范围内的变量。
- 上下文范围具有上下文范围的属性被插入到上下文中，并且持续时间与上下文一样长或直到它被清除。一旦定义，上下文范围内的属性就是上下文的一部分。因此，它可用于所有日志事件，包括通过序列化发送到远程主机的事件。
- 系统范围具有系统范围的属性被插入到JVM的系统属性中，并且持续时间与JVM一样长或直到它被清除。
```
// 默认LOCAL SCOPE
<property scope="context" name="nodeId" value="firstNode" />
```
首先在本地范围内查找属性，然后在上下文范围内查找，然后在系统属性范围内查找，最后在操作系统环境中查找。
> 可以使用 :- 运算符指定默认值。例如，假设未定义名为aName的变量，将被解释为golden。 "${aName:-golden}"
```
properties 文件
USER_HOME=/home/sebastien
fileName=myApp.log
destination=${USER_HOME}/${fileName}

${${userid}.password}
${id:-${userid}}
```
6. `<define name="additivity" class="com.ljt.study.CustomPropertyDefiner" />`
7. 条件处理
```
<if condition='property("HOSTNAME").contains("torino")'>
    <then>
      <appender name="CON" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
          <pattern>%d %-5level %logger{35} - %msg %n</pattern>
        </encoder>
      </appender>
      <root>
        <appender-ref ref="CON" />
      </root>
    </then>
</if>
```
8. include 目标文件必须将其元素嵌套在一个`<included>`元素内
```
<include resource="includedConfig.xml"/>
<include url="http://some.host.com/includedConfig.xml"/>

<included>
  <appender name="includedConsole" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>"%d - %m%n"</pattern>
    </encoder>
  </appender>
</included>
```
9. `<timestamp key="bySecond" datePattern="yyyyMMddHHmmss" timeReference="contextBirth"/>`  
timeReference属性是可选的。默认是配置文件的解析时间，即当前时间。但是，在某些情况下，使用上下文创建时间可能会很有用。这可以通过将timeReference设置为"contextBirth"来实现。