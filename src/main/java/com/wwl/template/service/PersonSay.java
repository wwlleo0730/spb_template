package com.wwl.template.service;

import org.springframework.stereotype.Component;

@Component
public class PersonSay{
	
	public String say(){
		return "Hello! World";
	}
	
	public String say(String name){
		return "Hello! "+ name;
	}

}
