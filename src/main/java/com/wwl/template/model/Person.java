package com.wwl.template.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
	/**
	 *    可以离职的业务逻辑，属于内聚的业务，放在model内部。
	 *    如果年龄大于40岁，不可以离职。  
	 *   
	 * @return
	 */
	public boolean canLeave() {
		
		if(this.getAge() > 40) {
			return false;
		} else return true;
		
	}

}
