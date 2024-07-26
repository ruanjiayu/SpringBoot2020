package com.fun.uncle.springboot2020.annotation;


import com.fun.uncle.springboot2020.constant.RegexConstant;
import com.fun.uncle.springboot2020.utils.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description: 时间格式校验 方式一
 * @使用说明： 在请求体内使用注解，并且@Validated来进行生效
 * @ValidLocalDateTime
 * private String startTime;
 *
 * @Author: summer
 * @CreateDate: 2024/7/26 14:27
 * @Version: 1.0.0
 */


@Documented
@Constraint(validatedBy = CustomTimeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalDateTime {
    String message() default "时间格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class CustomTimeValidator implements ConstraintValidator<ValidLocalDateTime, String> {
    @Override
    public void initialize(ValidLocalDateTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 允许null或空字符串
        if (StringUtils.isBlank(value)) {
            return true;
        }
        // 使用正则表达式进行验证
        return value.matches(RegexConstant.TIME_REGEX);
    }
}