package com.wwl.common.utils;

import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * 响应结果生成工具
 */
@Slf4j
public class ResultGenerator {

	private static final String DEFAULT_SUCCESS_MESSAGE = "success";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Result genResult(Object data) {
		Result result = Result.newInstance();
		result.setCode(HttpStatus.OK.value());
		result.setData(data);
		result.setMessage(DEFAULT_SUCCESS_MESSAGE);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Result genResult(Integer code, String message) {
		Result result = Result.newInstance();
		result.setCode(code);
		result.setData(null);
		result.setMessage(message);
		if (log.isDebugEnabled()) {
			log.debug("generate rest result:{}", result);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result genErrorResult(Integer code , String message) {
		return genResult(code, message);
	}

	/**
	 * error message
	 * 
	 * @param message
	 *            error message
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Result genErrorResult(String message) {
		return genResult(HttpStatus.BAD_REQUEST.value() , 
				message);
	}

	/**
	 * success no message
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result<String> genSuccessResult() {
		return genResult(HttpStatus.OK.value(), "success");
	}

	@SuppressWarnings("rawtypes")
	public static Result genSuccessResult(Object data) {
		return genResult(data);
	}

}
