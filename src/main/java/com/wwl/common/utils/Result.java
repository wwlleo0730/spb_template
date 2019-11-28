package com.wwl.common.utils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
@ApiModel(value="api返回体")
public class Result<T> {

	private Integer code;
	private T content;
	private String message;
	
	public Result() {

	}
	
	public Result(T content) {
        this.code = 200;
        this.message = "success";
        this.content = content;
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
