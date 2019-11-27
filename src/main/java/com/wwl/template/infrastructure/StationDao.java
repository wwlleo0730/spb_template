package com.wwl.template.infrastructure;

import org.springframework.stereotype.Repository;

import com.wwl.template.model.Station;

@Repository
public interface StationDao extends BaseDao<Station , Integer> {

}
