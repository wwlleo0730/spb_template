package com.wwl.template.infrastructure;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T extends Persistable<ID>, ID extends Serializable>
		extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {

}
