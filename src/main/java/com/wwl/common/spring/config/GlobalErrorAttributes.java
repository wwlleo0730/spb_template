package com.wwl.common.spring.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.wwl.common.exception.ServiceException;


/**
 * 修改 spring mvc 默认错误结构
 * 
 * 原结构为：
 * {
 *   "timestamp": 1574918552333,
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "No message available",
 *   "path": "/api/xxx1/xxx2"
 * }
 * @author wuwl
 * @version 2018年12月28日
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
	
	public GlobalErrorAttributes() {
        super(false);
    }
	
	public Map<String, Object> assembleError(HttpStatus status , WebRequest request) {
		
		Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Throwable error = getError(request);
		
        if (error instanceof ServiceException) {
            errorAttributes.put("code", ((ServiceException) error).getCode());
            errorAttributes.put("content", error.getMessage());
            errorAttributes.put("message", error.getMessage());
        } else {
        	
        	if(null != status) {
        		 errorAttributes.put("code", status.value());
                 errorAttributes.put("content", status.getReasonPhrase());
                 errorAttributes.put("message", status.getReasonPhrase());
        	}
        }   
        return errorAttributes;
	}
    
}

