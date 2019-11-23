package com.wwl.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwl.template.infrastructure.PersonDao;
import com.wwl.template.model.Person;

import io.swagger.annotations.ApiOperation;

@RestController
public class PersonController {
	
	@Autowired
	private PersonDao personDao;
	
	@GetMapping("/persons")
	@ApiOperation(value = "all persons")
	public List<Person> allPersons() {
		return personDao.findAll();
	}
	
}

