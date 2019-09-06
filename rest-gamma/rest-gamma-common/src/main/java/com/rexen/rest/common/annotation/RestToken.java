package com.rexen.rest.common.annotation;

import java.lang.annotation.*;

/**
 * User token
 *
 * @author: GavinHacker
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestToken {
    /**
     *  value
     */
    String value() default "";
}