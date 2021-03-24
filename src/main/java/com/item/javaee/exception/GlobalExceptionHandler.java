package com.item.javaee.exception;

import com.item.javaee.utils.JsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 15:44
 * @Version: 1.0
 **/
//@RestControllerAdvice(basePackages = "com.item.javaee.controller")
public class GlobalExceptionHandler {
    private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class) ;

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest req, HttpServletResponse resp,
                                   Exception e) {
        return JsonBuilder.buildJson(e, 302, "添加同一个tag") ;
    }
}
