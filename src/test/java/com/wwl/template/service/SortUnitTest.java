package com.wwl.template.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.google.common.collect.Lists;
import com.wwl.template.BaseSpringBootTest;

@SpringBootApplication(scanBasePackages = "com.wwl.template.service")
public class SortUnitTest extends BaseSpringBootTest {
	
	@Autowired
	@Qualifier("JVMSort")
	private ISort jvmSort;
	
	@Autowired
	@Qualifier("BubbleSort")
	private ISort bubbleSort;
	
	@Test
	public void testSort() {
		
		List<Integer> nums = Lists.newArrayList(1,3,7,9,2,5,8);
		
		Assertions.assertThat(jvmSort.sort(nums)).isSorted();
		Assertions.assertThat(bubbleSort.sort(nums)).isSorted();
	}
	
}
