package com.ljt.study;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiJingTang
 * @date 2021-12-23 14:33
 */
public class LogbackTest {

    @Test
    public void helloWorld() {
        Logger log = LoggerFactory.getLogger("hello-world");
        log.debug("Hello Logback");
    }

    @Test
    public void singleton() {
        Logger log1 = LoggerFactory.getLogger(LogbackTest.class);
        Logger log2 = LoggerFactory.getLogger(LogbackTest.class);
        assert log1 == log2;
    }

    @Test
    public void statusPrinter() {
        Logger log = LoggerFactory.getLogger("status-printer");

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        log.debug("Hello StatusPrinter");
    }

    @Test
    public void statusManager() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusManager statusManager = lc.getStatusManager();
        OnConsoleStatusListener onConsoleListener = new OnConsoleStatusListener();
        statusManager.add(onConsoleListener);
    }

    @Test
    public void level() {
        Logger com = LoggerFactory.getLogger("com");
        com.trace(Level.TRACE.levelStr);
        com.debug(Level.DEBUG.levelStr);
        com.info("不设置级别 默认是DEBUG 所以TRACE日志没有输出");

        ch.qos.logback.classic.Logger com1 = (ch.qos.logback.classic.Logger) com;
        // 修改级别
        com1.setLevel(Level.INFO);

        Logger ljt = LoggerFactory.getLogger("com.ljt");
        ljt.debug(Level.DEBUG.levelStr);
        ljt.info(Level.INFO.levelStr);
        ljt.warn("父级修改了级别为info后 当前debug日志不输出了");

        ch.qos.logback.classic.Logger ljt1 = (ch.qos.logback.classic.Logger) ljt;
        ljt1.setLevel(Level.TRACE);

        Logger study = LoggerFactory.getLogger("com.ljt.study");
        study.trace("父类修改了级别后 trace级别日志也输出了");
        study.debug(Level.DEBUG.levelStr);
    }

    @Test
    public void additivity() {
        Logger log = LoggerFactory.getLogger("additivity");
        log.info("Appender 默认时累加的 可以设置additivity=\"false\"");
    }

    @Test
    public void contextName() {
        Logger log = LoggerFactory.getLogger("contextName");
        log.info("contextName define timestamp");
    }

}
