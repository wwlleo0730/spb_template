package com.wwl.template.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwl.common.utils.Result;
import com.wwl.template.service.PersonSay;

import io.swagger.annotations.ApiOperation;

@RestController
public class Hello {
	
	@Resource
	private PersonSay say;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/say")
	@ApiOperation("say hello")
	public Result<String> hello(){
		return Result.success(say.say());
	}
	
	
	@PostMapping("/sayto/{name}")
	@ApiOperation("say hello to someone")
	public Result<String> helloto(@PathVariable("name") String name){
		return Result.success(say.say(name));
	}
	
	
}

