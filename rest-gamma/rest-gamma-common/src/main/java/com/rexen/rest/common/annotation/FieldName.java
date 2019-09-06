package com.rexen.rest.common.annotation;

import java.lang.annotation.*;

/**
 * Description：字段注解
 *
 * @author GavinHacker
 * @since Created in 上午10:47 2019/5/20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldName {

    String value() default "";

    String description() default "";
}
