package com.team9.servicebase.exceptionhandler;

import com.team9.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j //为了把log写到日志中
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }

    //自定义异常
    @ExceptionHandler(EasyPcException.class)
    @ResponseBody
    public R error(EasyPcException e){
        e.printStackTrace();
        log.error(e.getMessage()); //为了把log写到日志中
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
