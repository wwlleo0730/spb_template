package com.wwl.template.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BubbleSort implements ISort {

	@Override
	public List<Integer> sort(List<Integer> list) {
		
		//共需要排size-1轮
		for (int i = 0; i < list.size() - 1; i++) {

			// 每一轮排 size-i-1 次
			for (int j = 0; j < list.size() - i - 1; j++) {

				Integer temp;
				if (list.get(j) > list.get(j + 1)) {
					temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}

		return list;
	}

}
