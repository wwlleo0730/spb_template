package com.wwl.template.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.wwl.template.model.Person;
import com.wwl.template.model.Station;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonDaoTest {
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testFindAll() {
		assertThat(personDao.findAll().size()).isEqualTo(6);
	}
	
	@Test
	public void testFindByName() {
		assertThat(personDao.findByName("jim").size()).isEqualTo(1);
	}
	
	@Test
	public void testInsertAndUpdate() {
		Person person = new Person();
		person.setAge(50);
		person.setName("john");
		person.setStation(new Station(2));
		assertThat(person.isNew()).isEqualTo(true);
		this.personDao.save(person);
		assertThat(person.getId()).isEqualTo(7);		
		person.setAge(49);
		this.personDao.save(person);
		
		assertThat(person.myUnitName()).isEqualTo("develop");
	}

}
