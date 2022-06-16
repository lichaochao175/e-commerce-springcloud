package com.lcc.annotation;

/**
 * @author lichaochao
 * @data 2022/4/11 10:18 AM
 **/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略统一响应定义
 */
//元注解
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)//三种形式 ： 保存源文件 ， 保存运行时
public @interface IgnoreResponseAdvice {
}
