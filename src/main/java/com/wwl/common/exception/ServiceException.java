package com.wwl.common.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	private Integer httpCode;
	
	public Integer getCode() {
		return code;
	}
	
	public Integer getHttpCode() {
		return httpCode;
	}

	public ServiceException() {
    }
	
	public ServiceException(Throwable t) {
		super(t);
	}
	
	public ServiceException(HttpStatus error) {
		super(error.getReasonPhrase());
		this.httpCode = error.value();
        this.code = error.value();
	}
	
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message , Integer httpCode) {
        super(message);
        this.httpCode = httpCode;
        this.code = httpCode;
    }
    
    public ServiceException(String message , HttpStatus status) {
        super(message);
        this.httpCode = status.value();
        this.code = status.value();
    }
    
    /**
     * 
     * @param message
     * @param httpCode http返回码
     * @param code 具体业务错误码
     */
    public ServiceException(String message , Integer httpCode , Integer code) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}