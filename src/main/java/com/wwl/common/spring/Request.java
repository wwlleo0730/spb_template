package com.wwl.common.spring;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuwl
 * @version 2019年2月18日
 */

@Slf4j
@Service
public class Request {

	@Autowired
	private RestTemplate restTemplate;

	public Request() {

	}

	public Request(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private HttpHeaders makeHeaders(String tokenName, String tokenValue) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add(HttpHeaders.USER_AGENT , "");
		if(StringUtils.isNotBlank(tokenName)){
			headers.add(tokenName, tokenValue);
		}
		return headers;
	}

	/**
	 * 
	 * @param url
	 * @param tokenName
	 * @param tokenValue
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doGet(String url, String tokenName, String tokenValue) {
		HttpEntity request = new HttpEntity(null, makeHeaders(tokenName, tokenValue));
		try {
			ResponseEntity<String> response = 
					restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			log.error("error: " + e.getStatusCode().value() + " , error reason : " + e.getResponseBodyAsString());
			throw new RuntimeException(e.getResponseBodyAsString());
		} catch (Exception e) {
			log.error("error: " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doGet(String url, String tokenName, String tokenValue , Map params) {
		
		HttpEntity request = new HttpEntity(null, makeHeaders(tokenName, tokenValue));
		try {
			ResponseEntity<String> response = 
					restTemplate.exchange(url, HttpMethod.GET, request, String.class , params);
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			log.error("error: " + e.getStatusCode().value() + " , error reason : " + e.getResponseBodyAsString());
			throw new RuntimeException(e.getResponseBodyAsString());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param url
	 * @param params post内容参数Map
	 * 
	 *  Map<String, String> params = new HashMap<String, String>();
		params.put("param1", "v1");
		params.put("param2", "v2");
		params.put("param3", "v3");
	 * 
	 * @param tokenName
	 * @param tokenValue
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doPost(String url, Map params, String tokenName, String tokenValue) {
		HttpEntity request = new HttpEntity(params, makeHeaders(tokenName, tokenValue));
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			log.error("error: " + e.getStatusCode().value() + " , error reason : " + e.getResponseBodyAsString());
			throw new RuntimeException(e.getResponseBodyAsString());
		} catch (Exception e) {
			log.error("error: " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
}
