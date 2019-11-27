package com.wwl.template.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wwl.template.infrastructure.PersonDao;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest  {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private PersonDao personDao; 
	
	@Test
	public void testAllPersons() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(content().json("{\"code\":200,\"data\":\"hello\",\"message\":\"success\"}"))
			.andExpect(status().isOk());
	}
	

}
