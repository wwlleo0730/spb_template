package com.wwl.common.utils;

import static org.assertj.core.api.Assertions.assertThat;

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
		
		
		Date testDay = DateUtil.StringToDateTime("2020-01-10 12:00:05");
		
		// 2天后
		assertThat(DateUtil.dayOffset(testDay , 2))
							.isEqualTo(DateUtil.StringToDateTime("2020-01-12 12:00:05"));
		
		// 2秒后
		assertThat(DateUtil.secondOffset(testDay, 2))
							.isEqualTo(DateUtil.StringToDateTime("2020-01-10 12:00:07"));
		
	}

}
