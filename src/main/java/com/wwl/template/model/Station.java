package com.wwl.template.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Station extends BaseEntity {

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@OneToMany(mappedBy = "station", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Person> persons;

	public Station(Integer pk) {
		this.setId(pk);
	}
	
	/**
	 *    当前职位下的员工
	 * @return
	 */
	public List<Person> activePersons() {
		return this.persons;
	}

}
