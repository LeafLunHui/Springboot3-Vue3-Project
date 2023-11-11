package com.athuyanhong.validation;

import com.athuyanhong.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 22:23
 * @Package_Name com.athuyanhong.validation
 * @Project_Name SpringBoot-Vue-Program
 */
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return false;
        }
        if(s.equals("已发布") || s.equals("草稿")){
            return true;
        }
        return false;
    }
}
