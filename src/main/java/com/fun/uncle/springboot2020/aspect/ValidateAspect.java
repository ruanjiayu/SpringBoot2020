package com.fun.uncle.springboot2020.aspect;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import com.fun.uncle.springboot2020.annotation.Validate;
import com.fun.uncle.springboot2020.config.exception.BizErrorException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class ValidateAspect {

    private final Validator validator = new Validator();

    @Pointcut("execution(* com.fun.uncle.*.controller..*.*(..))")
    public void pointCut() {
        // nothing to do
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        this.batchValidate(method.getParameters(), joinPoint.getArgs());
    }

    private void batchValidate(Parameter[] parameters, Object[] args) {
        if (ArrayUtil.isEmpty(parameters)) {
            return;
        }

        List<String> allValidateRes = new ArrayList<>();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(Validate.class)) {
                Validate annotation = AnnotationUtils.getAnnotation(parameters[i], Validate.class);
                if (null == annotation) {
                    continue;
                }

                List<String> validateRes = this.doValidate(annotation, args[i]);
                allValidateRes.addAll(validateRes);
            }
        }

        if (CollectionUtils.isEmpty(allValidateRes)) {
            return;
        }

        String errMsg = String.join(",", allValidateRes);
        // todo:: 提示校验异常
        throw new BizErrorException(errMsg);
    }

    private List<String> doValidate(Validate annotation, Object arg) {

        String profile = annotation.profile();
        List<String> validateRes = new ArrayList<>();

        if (arg instanceof Collection) {
            for (Object element : (Collection) arg) {
                List<ConstraintViolation> validate = validator.validate(element, CharSequenceUtil.isEmpty(profile) ? null : profile);
                for (ConstraintViolation next : validate) {
                    validateRes.add(next.getMessage());
                }
            }
        } else {
            List<ConstraintViolation> validate = validator.validate(arg, CharSequenceUtil.isEmpty(profile) ? null : profile);
//            List<ConstraintViolation> validate = CharSequenceUtil.isEmpty(profile) ? validator.validate(arg) : validator.validate(arg, profile);
            for (ConstraintViolation next : validate) {
                validateRes.add(next.getMessage());
            }
        }

        return validateRes;
    }

}
