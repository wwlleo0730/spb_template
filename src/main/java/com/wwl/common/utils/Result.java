package com.wwl.common.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 统一API响应结果封装
 */
@Getter
@Setter
@ToString
public class Result<T> {

	private int code;
	private T data;
	private String message;
	
	public Result() {

	}
	
	public Result(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }

	@SuppressWarnings("rawtypes")
	public static Result newInstance() {
		return new Result();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result success(){
		return new Result("success");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result success(String text){
		return new Result(text);
	}
	
	public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
}
