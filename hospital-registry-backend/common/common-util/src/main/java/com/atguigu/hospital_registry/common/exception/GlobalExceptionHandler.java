package com.atguigu.hospital_registry.common.exception;

import com.atguigu.hospital_registry.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(HospitalRegistryException.class)
    @ResponseBody
    public Result error(HospitalRegistryException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
