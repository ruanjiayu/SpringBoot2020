package com.fun.uncle.springboot2020.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fun.uncle.springboot2020.annotation.check.IsDateCheck;
import com.fun.uncle.springboot2020.constant.enums.DateTimeTypeEnum;
import net.sf.oval.ConstraintTarget;
import net.sf.oval.configuration.annotation.Constraint;
import net.sf.oval.configuration.annotation.Constraints;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
    checkWith = IsDateCheck.class
)
@Repeatable(IsDate.List.class)
public @interface IsDate {
    ConstraintTarget[] appliesTo() default {ConstraintTarget.VALUES};

    String errorCode() default "com.hailiang.hr.common.oval.annotations.IsDate";

    String message() default "日期格式不正确";

    DateTimeTypeEnum dateTimeType();

    String format();

    String[] profiles() default {};

    String when() default "";


    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Constraints
    public @interface List {
        IsDate[] value();

        String when() default "";
    }
}
