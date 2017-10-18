package com.mariston.food.annotation;

import java.lang.annotation.*;

/**
 * table annotation
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

    /**
     * table name
     *
     * @return name
     */
    String name();

    /**
     * primary key
     *
     * @return key
     */
    String key();

    /**
     * time range property
     *
     * @return field name
     */
    String time() default "";
}
