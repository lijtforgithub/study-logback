package com.ljt.study;

import ch.qos.logback.classic.Level;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJingTang
 * @date 2021-12-24 16:55
 */
public class RollingFileAppenderTest {

    @BeforeClass
    public static void beforeEach () {
        System.setProperty(Level.DEBUG.levelStr, Boolean.TRUE.toString());
    }

    @Test
    public void timeBasedRollingPolicy() throws InterruptedException {
        Logger log = LoggerFactory.getLogger("TimeBasedRollingPolicy");

        while (true) {
            int minute = LocalTime.now().getMinute();
            if (minute % 2 == 0) {
                log.info(new Date().toString());
            }
            TimeUnit.SECONDS.sleep(10);
        }
    }

    @Test
    public void sizeAndTimeBasedRollingPolicy() throws InterruptedException {
        Logger log = LoggerFactory.getLogger("SizeAndTimeBasedRollingPolicy");

        while (true) {
            log.info(new Date().toString());
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

}
