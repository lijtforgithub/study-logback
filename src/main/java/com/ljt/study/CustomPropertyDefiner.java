package com.ljt.study;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * @author LiJingTang
 * @date 2021-12-24 10:20
 */
public class CustomPropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        return Boolean.FALSE.toString();
    }

}
