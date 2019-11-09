package com.wwl.common.utils;

import static org.assertj.core.api.Assertions.assertThat;

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
	}

}
