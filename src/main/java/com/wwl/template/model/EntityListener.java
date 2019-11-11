package com.wwl.template.model;

import java.util.Date;

import javax.persistence.PreUpdate;

public class EntityListener {

	@PreUpdate
	public static void preUpdate(BaseEntity baseEntity) {
		baseEntity.setLastModifiedDate(new Date());
	}

}
