package com.athuyanhong.annotation;

import com.athuyanhong.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 22:19
 * @Package_Name com.athuyanhong.annotation
 * @Project_Name SpringBoot-Vue-Program
 */

@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

    //提供校验失败后的信息.
    String message() default "当前的值必需为已发布或者草稿";

    //指定分组
    Class<?>[] groups() default {};

    //获取导State注解的附加信息
    Class<? extends Payload>[] payload() default {};

}
