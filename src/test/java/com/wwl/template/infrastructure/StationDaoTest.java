package com.wwl.template.infrastructure;

import javax.transaction.Transactional;

import org.apache.maven.doxia.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
@Transactional
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
