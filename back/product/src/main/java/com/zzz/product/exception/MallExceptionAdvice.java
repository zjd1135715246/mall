package com.zzz.product.exception;

import com.zzz.common.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MallExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentException(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        Map<String,String> errorMap = new HashMap<>();

        result.getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return R.error(400,"数据校验出现问题").put("error",errorMap);
    }

    @ExceptionHandler(Throwable.class)
    public R handlerException(){
        return R.error();
    }

}
