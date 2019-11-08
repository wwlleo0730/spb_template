package com.wwl.common.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.wwl.common.utils.Result;
import com.wwl.common.utils.ResultWapper;

import lombok.extern.slf4j.Slf4j;

/**
* @author wuwl
* @version 2019年1月23日
*/
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ResponseDataHandler extends AbstractMappingJacksonResponseBodyAdvice {@Override
	
	/**
     * 后处理，在方法有RestWrapper注解时，包装return type
     */
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, 
			MediaType contentType , MethodParameter returnType,
			ServerHttpRequest arg3, ServerHttpResponse arg4) {
		
		// check ResultWapper annotation
		if (returnType.getMethod().isAnnotationPresent(ResultWapper.class)) {
			Object raw = bodyContainer.getValue();
			Result result = Result.success(raw);
			bodyContainer.setValue(result);
		}
	}

}
