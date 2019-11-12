package com.wwl.template.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.wwl.template.model.Person;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class PersonDaoTest {
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testFindAll() {
		personDao.findAll().forEach( t->{
			log.info(t.toString());
		});
	}
	
	@Test
	public void testInsert() {
		Person person = new Person();
		person.setAge(50);
		person.setName("Jhon");
		person.setStation_id(2);
		this.personDao.save(person);
	}

}
