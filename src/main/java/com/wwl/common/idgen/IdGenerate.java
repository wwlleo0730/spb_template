/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.wwl.common.idgen;

import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author ThinkGem
 * @version 2014-8-19
 */
public class IdGenerate {

	private static SecureRandom random = new SecureRandom();
	private static IdWorker idWorker = new IdWorker(-1, -1);

	/**
	 * 生成UUID, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 获取新唯一编号（18为数值） 来自于twitter项目snowflake的id产生方案，全局唯一，时间有序。 64位ID
	 * (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
	 */
	public static String nextId() {
		return String.valueOf(idWorker.nextId());
	}

	/**
	 * 获取新代码编号
	 */
	public static String nextCode(String code) {
		if (code != null) {
			String str = code.trim();
			int len = str.length() - 1;
			int lastNotNumIndex = 0;
			for (int i = len; i >= 0; i--) {
				if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
					lastNotNumIndex = i;
					break;
				}
			}
			// 如果最后一位是数字，并且last索引位置还在最后，则代表是纯数字，则最后一个不是数字的索引为-1
			if ((str.charAt(len) >= '0' && str.charAt(len) <= '9') && (lastNotNumIndex == len)) {
				lastNotNumIndex = -1;
			}
			String prefix = str.substring(0, lastNotNumIndex + 1);
			String numStr = str.substring(lastNotNumIndex + 1, str.length());
			long num = Long.parseLong(numStr);
//			System.out.println("处理前："+str);
			str = prefix + StringUtils.leftPad(String.valueOf(num + 1), numStr.length(), "0");
//			System.out.println("处理后："+str);
			return str;
		}
		return null;
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	public static String generateShortUUID() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateShortUUID());
	}

}
