package com.wwl.template.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.collect.Lists;
import com.wwl.template.infrastructure.PersonDao;
import com.wwl.template.model.Person;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@Slf4j
public class PersonControllerTest  {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private PersonDao personDao; 
	
	@Test
	public void testAllPersons() throws Exception {
		
		List<Person> list = Lists.newArrayList();
		
		IntStream.range(1, 5).forEach(t->{
			list.add(new Person( "k"+t , 
					NumberUtil.generateRandomNumber(20, 60 , 5)[0] ,
					null));
		});
		
		when(personDao.findAll()).thenReturn(list);
		 
		String responseString = mvc.perform(get("/persons"))
			.andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
		 
		log.info("{}" , responseString);
	}
	

}
