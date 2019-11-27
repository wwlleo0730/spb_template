package com.wwl.template.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2data/data.sql")
public class StationDaoTest {
	
	@Autowired
	private StationDao stationDao;

	@Test
	public void testFindAll() {
		
		stationDao.findAll().forEach( t-> {
			
			log.info("======================================");
			log.info("station name : {}" , t.getName() );
			
			t.activePersons().forEach( t1 -> {
				log.info("   -------- {}" , t1.getName());
			});
			
			log.info("=======================================");
		});
	}
	
}
