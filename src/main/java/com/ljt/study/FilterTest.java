package com.ljt.study;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.filter.ThresholdFilter;
import com.ljt.study.impl.SimpleFilter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiJingTang
 * @date 2021-12-27 9:37
 */
public class FilterTest {

    @Test
    public void simpleFilter() {
        Logger log = LoggerFactory.getLogger(SimpleFilter.class.getSimpleName());
        log.info("Hello " + SimpleFilter.class);
        log.info("Hello " + SimpleFilter.class.getSimpleName());
        log.info("Hello {}", SimpleFilter.class);
        log.info("Hello World");
    }

    @Test
    public void levelFilter() {
        Logger log = LoggerFactory.getLogger(LevelFilter.class.getSimpleName());
        log.trace(Level.TRACE.levelStr);
        log.debug(Level.DEBUG.levelStr);
        log.info(Level.INFO.levelStr);
        log.warn(Level.WARN.levelStr);
        log.error(Level.ERROR.levelStr);
    }

    @Test
    public void thresholdFilter() {
        Logger log = LoggerFactory.getLogger(ThresholdFilter.class.getSimpleName());
        log.trace(Level.TRACE.levelStr);
        log.debug(Level.DEBUG.levelStr);
        log.info(Level.INFO.levelStr);
        log.warn(Level.WARN.levelStr);
        log.error(Level.ERROR.levelStr);
    }

}
