package com.wwl.common.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.wwl.common.exception.ServiceException;


/**
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
            errorAttributes.put("data", error.getMessage());
            errorAttributes.put("message", error.getMessage());
        } else {
        	
        	if(null != status) {
        		 errorAttributes.put("code", status.value());
                 errorAttributes.put("data", status.getReasonPhrase());
                 errorAttributes.put("message", status.getReasonPhrase());
        	}
        }   
        return errorAttributes;
	}
    
}

