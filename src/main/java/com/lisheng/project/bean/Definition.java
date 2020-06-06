package com.lisheng.project.bean;



import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lisheng
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)

public @interface Definition {
    String targetName();
    Class<? extends BeanDefinition>  target() default DefaultBeanDefinitonImpl.class;
}
