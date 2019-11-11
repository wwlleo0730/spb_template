package com.wwl.template.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Person extends BaseEntity {
	
	private String name;
	
	private Integer age;
	
	/**
	 * fk station
	 */
	private Integer station_id;

}
