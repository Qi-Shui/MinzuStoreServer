package com.minzu.minzustore.exception;

import com.minzu.minzustore.entity.Msg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Msg exceptionHandler(HttpServletRequest request,Exception e){
        return Msg.error("服务端异常"+"<br/>"+e.getMessage());
    }
}
