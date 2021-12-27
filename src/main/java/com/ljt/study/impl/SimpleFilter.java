package com.ljt.study.impl;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author LiJingTang
 * @date 2021-12-27 9:59
 */
public class SimpleFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String msg = event.getMessage();
        if (msg.contains(SimpleFilter.class.getSimpleName())) {
            return FilterReply.ACCEPT;
        } else if (msg.contains("World")) {
            return FilterReply.NEUTRAL;
        } else {
            return FilterReply.DENY;
        }
    }

}
