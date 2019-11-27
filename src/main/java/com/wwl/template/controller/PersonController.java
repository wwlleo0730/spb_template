package com.wwl.template.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwl.common.utils.Result;
import com.wwl.common.utils.ResultWapper;
import com.wwl.template.infrastructure.PersonDao;
import com.wwl.template.model.Person;

import io.swagger.annotations.ApiOperation;

@RestController
public class PersonController {
	
	@Autowired
	private PersonDao personDao;
	
	@GetMapping("/hello")
	@ApiOperation(value = "hello")
	public Result<String> hello() {
		return Result.success("hello");
	}
	
	@GetMapping("/persons")
	@ApiOperation(value = "all persons")
	@ResultWapper
	public List<Person> allPersons() {
		return personDao.findAll();
	}
	
	@GetMapping("/personsCantFired")
	@ApiOperation(value = "persons cant fired")
	@ResultWapper
	public List<Person> personsCantFired() {
		
		List<Person> olds = personDao.findAll().parallelStream()
			.filter( t -> t.canLeave() == false )
			.collect(Collectors.toList());
		
		return olds;
	}
	
}

