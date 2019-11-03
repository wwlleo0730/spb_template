package com.wwl.common.config;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.wwl.common.exception.ServiceException;
import com.wwl.common.utils.Result;
import com.wwl.common.utils.ResultGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

	@ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e) {
		log.error(e.getMessage(), e);
		Result result = null;
        Integer httpCode = e.getHttpCode();
        Integer code = e.getCode();
        if (null == httpCode) {
            // 业务失败的异常，如“账号或密码错误”
            result = ResultGenerator.genErrorResult(HttpStatus.BAD_REQUEST.value() ,
            		e.getMessage());
        } else {
            result = ResultGenerator.genErrorResult(code, e.getMessage());
        }
        return result;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
    	log.error(e.getMessage(), e);
    	Result result = ResultGenerator.genErrorResult(HttpStatus.NOT_FOUND.value(),
                "接口 [" + request.getRequestURI() + "] 不存在");
        return result;
    }

    @ExceptionHandler(ServletException.class)
    public Result handleServletException(ServletException e) {
        log.error(e.getMessage(), e);
        Result result = ResultGenerator.genErrorResult(HttpStatus.BAD_REQUEST.value() ,
        		e.getMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request, Object handler) {
        log.error(e.getMessage(), e);
        Result result = null;
        result = ResultGenerator.genErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
        String message;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
                    handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(),
                    e.toString());
            result.setMessage(message);
        } else {
            message = e.getMessage();

            result.setMessage(message);
        }
        return result;
    }


}
