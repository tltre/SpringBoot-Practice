package org.example.exception;

import org.example.domain.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult handleException(Exception e){
        // 获取异常信息，存放进msg中
        String msg = e.getMessage();
        return new ResponseResult(300, msg);
    }
}
