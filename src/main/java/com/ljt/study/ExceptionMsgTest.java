package com.ljt.study;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LiJingTang
 * @date 2021-12-27 15:12
 */
public class ExceptionMsgTest {

    @Test
    public void exce() {
        Logger log = LoggerFactory.getLogger("exce");

        log.info("一条输出日志");
        log.error("打印异常", new NullPointerException("空指针"));
    }

}
