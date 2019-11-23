package com.wwl.common.utils;

import java.util.Date;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtilTest {
	
	@Test
	public void utilsTest() {
		log.info("thisyear: {}",DateUtil.currentYear());
		log.info("thismonth: {}",DateUtil.currentMonth());
		log.info("currentDateStr: {}" , DateUtil.currentDateStr());
		log.info("currentDateTimeStr: {}" , DateUtil.currentDateTimeStr());
		
		log.info(DateUtil.DateToString(DateUtil.dayOffset(new Date() , 2)));
		log.info(DateUtil.DateToString(DateUtil.monthOffset(new Date() , 2)));
		log.info(DateUtil.DateToString(DateUtil.yearOffset(new Date() , 2)));
		
	}

}
