package com.mariston.food.annotation;

import java.lang.annotation.*;

/**
 * data type annotation
 *
 * @author mariston
 * @version V1.0
 * @since 2017/10/17
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataType {

    /**
     * data type
     *
     * @return type
     */
    String value() default "nvarchar(200)";

}
