package com.wwl.template.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service(value = "JVMSort")
public class JVMSort implements ISort {

	@Override
	public List<Integer> sort(List<Integer> list) {
		return list.stream().sorted().collect(Collectors.toList());
	}

}
