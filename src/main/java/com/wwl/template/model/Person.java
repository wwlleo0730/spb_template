package com.wwl.template.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Person extends BaseEntity {
	
	private String name;
	
	private Integer age;
	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
	@JsonIgnoreProperties("persons")
	private Station station;
	
	/**
	 * my unit name
	 * @return
	 */
	public String myUnitName() {
		return this.getStation().getUnit().getName();
	}

}
