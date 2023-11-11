package com.athuyanhong.exception;

import com.athuyanhong.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 15:10
 * @Package_Name com.athuyanhong.exception
 * @Project_Name SpringBoot-Vue-Program
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage():"操作失败!");
    }

}
