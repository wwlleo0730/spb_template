package com.wwl.template;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.wwl.template.service.BubbleSort;
import com.wwl.template.service.ISort;
import com.wwl.template.service.JVMSort;

public class SortUnitTest {
	
	@Test
	public void testSort() {
		
		ISort jvmSort = new JVMSort();
		ISort bubbleSort = new BubbleSort();
		
		List<Integer> nums = Lists.newArrayList(1,3,7,9,2,5,8);
		
		Assertions.assertThat(jvmSort.sort(nums)).isSorted();
		Assertions.assertThat(bubbleSort.sort(nums)).isSorted();
	}
	
}
