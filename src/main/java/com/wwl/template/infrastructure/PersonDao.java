package com.wwl.template.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wwl.template.model.Person;

@Repository
public interface PersonDao extends BaseDao<Person, Integer> {
	
	//jpa example
	List<Person> findByName(String name);
	
}
