package com.wwl.template.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.wwl.template.model.Person;
import com.wwl.template.model.Station;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2data/data.sql")
public class PersonDaoTest {
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testFindAll() {
		assertThat(personDao.findAll().size()).isEqualTo(6);
	}
	
	@Test
	public void testFindByName() {
		
		List<Person> persons = personDao.findByName("jim");
				
		assertThat(persons.get(0).myUnitName()).isEqualTo("develop");
		assertThat(persons.size()).isEqualTo(1);
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
	}

}
