package com.wwl.template.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Unit extends BaseEntity {
	
	/**
	 *    部门名称
	 */
	private String name;
	
}
