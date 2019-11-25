package com.wwl.template.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonTest {

	@Test
	public void testCreate() {
		
		Person person = new Person();
		
		Station station = new Station();
		Unit unit = new Unit();
		
		person.setId(10);
		person.setName("anna");
		person.setStation(null);
		person.setAge(45);
		
		log.info(person.toString());
		
		assertThat(person.canLeave()).isEqualTo(false);
		
		
		
	}
}
