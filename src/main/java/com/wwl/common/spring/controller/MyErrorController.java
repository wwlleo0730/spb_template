package com.wwl.common.spring.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.wwl.common.spring.config.GlobalErrorAttributes;

@Controller
public class MyErrorController extends BasicErrorController {

	private GlobalErrorAttributes globalErrorAttributes;
	
	@Autowired
	public MyErrorController(GlobalErrorAttributes globalErrorAttributes ,
			ErrorAttributes errorAttributes , ServerProperties serverProperties ) {
		super(errorAttributes, serverProperties.getError());
		this.globalErrorAttributes = globalErrorAttributes;
	}

	@Override
	protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		HttpStatus status = super.getStatus(request);
		WebRequest webRequest = new ServletWebRequest(request);
		return globalErrorAttributes.assembleError(status, webRequest);
	}

	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections
				.unmodifiableMap(super.getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}

}
