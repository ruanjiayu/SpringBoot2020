package com.fun.uncle.springboot2020.annotation.check;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fun.uncle.springboot2020.annotation.IsDate;
import com.fun.uncle.springboot2020.constant.enums.DateTimeTypeEnum;
import net.sf.oval.ValidationCycle;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.exception.OValException;

/**
 * @Description: 时间检查校验
 * @Author: summer
 * @CreateDate: 2024/7/26 14:44
 * @Version: 1.0.0
 */
public class IsDateCheck extends AbstractAnnotationCheck<IsDate> {
    private transient DateTimeFormatter formatter;
    private DateTimeTypeEnum dateTimeTypeEnum;

    public IsDateCheck() {
    }

    @Override
    public void configure(IsDate isDate) {
        super.configure(isDate);
        this.formatter = DateTimeFormatter.ofPattern(isDate.format());
        this.dateTimeTypeEnum = isDate.dateTimeType();
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, ValidationCycle cycle) throws OValException {
        if (null == valueToValidate) {
            return true;
        } else {
            try {
                switch (this.dateTimeTypeEnum) {
                    case DATE:
                        LocalDate.parse(valueToValidate.toString(), this.formatter);
                        break;
                    case TIME:
                        LocalTime.parse(valueToValidate.toString(), this.formatter);
                        break;
                    case DATETIME:
                        LocalDateTime.parse(valueToValidate.toString(), this.formatter);
                }

                return true;
            } catch (Exception var5) {
                return false;
            }
        }
    }
}
