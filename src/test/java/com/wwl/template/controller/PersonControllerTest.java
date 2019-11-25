package com.wwl.template.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.wwl.template.BaseSpringMvcTest;
import com.wwl.template.infrastructure.PersonDao;


public class PersonControllerTest extends BaseSpringMvcTest {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private PersonDao personDao; 
	
	@Test
	public void testAllPersons() {
		
		System.out.println("1");
		
	}

}
