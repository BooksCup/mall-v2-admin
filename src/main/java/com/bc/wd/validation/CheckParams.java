package com.bc.wd.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-25 23:14
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckParams {
    // 多个CheckParam，由上往下判断
    CheckParam[] value();
}
