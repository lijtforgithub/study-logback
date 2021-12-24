package com.ljt.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJingTang
 * @date 2021-12-24 16:55
 */
public class TimeBasedRollingPolicyTest {

    private static final Logger log = LoggerFactory.getLogger(TimeBasedRollingPolicyTest.class);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            log.info("Test");
        }
    }

}
