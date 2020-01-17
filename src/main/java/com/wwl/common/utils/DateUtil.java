package com.wwl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;

public class DateUtil {

	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	public static final String DEFAULT_PATTERN_DAY = DatePattern.NORM_DATE_PATTERN;
	public static final String DEFAULT_PATTERN_DAY_TIME = DatePattern.NORM_DATETIME_PATTERN;

	private static final Object object = new Object();

	/**
	 * @return 今年
	 */
	public static Integer currentYear() {
		return cn.hutool.core.date.DateUtil.thisYear();
	}

	/**
	 * @return 当前真实月份，不是从0开始
	 */
	public static Integer currentMonth() {
		return cn.hutool.core.date.DateUtil.thisMonth() + 1;
	}

	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @return 当前日期，形如：2019-01-01
	 */
	public static String currentDateStr() {
		Date date = new Date();
		return DateToString(date, DEFAULT_PATTERN_DAY);
	}

	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @return 当前日期，形如：2019-01-01 12:12:12
	 */
	public static String currentDateTimeStr() {
		return cn.hutool.core.date.DateUtil.now();
	}

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 * @return
	 * @throws RuntimeException
	 */
	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	public static Date StringToDate(String date) {
		return StringToDate(date, DEFAULT_PATTERN_DAY);
	}

	public static Date StringToDateTime(String date) {
		return StringToDate(date, DEFAULT_PATTERN_DAY_TIME);
	}

	/**
	 * 字符串转date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date StringToDate(String date, String pattern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(pattern).parse(date);
			} catch (Exception e) {
			}
		}
		return myDate;
	}

	public static String DateToString(Date date) {
		return DateToString(date, DEFAULT_PATTERN_DAY);
	}

	public static String DateTimeToString(Date date) {
		return DateToString(date, DEFAULT_PATTERN_DAY_TIME);
	}

	/**
	 * date转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String DateToString(Date date, String pattern) {
		return cn.hutool.core.date.DateUtil.format(date, pattern);
	}

	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date, DEFAULT_PATTERN_DAY), 
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	public static double getIntervalYears(String date, String otherDate) {
		return getIntervalYears(StringToDate(date, DEFAULT_PATTERN_DAY), 
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	public static int getIntervalYearsInt(String date, String otherDate) {
		return getIntervalYearsInt(StringToDate(date, DEFAULT_PATTERN_DAY),
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	/**
	 * 两个日期计算相差天数
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		int num = 0;
		Date dateTmp = StringToDate(DateToString(date, DEFAULT_PATTERN_DAY), DEFAULT_PATTERN_DAY);
		Date otherDateTmp = StringToDate(DateToString(otherDate, DEFAULT_PATTERN_DAY), DEFAULT_PATTERN_DAY);
		if (dateTmp != null && otherDateTmp != null) {
			long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}

	/**
	 * 两个日期相差年数
	 * 保留一位小数，返回float
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static double getIntervalYears(Date date, Date otherDate) {
		double k = getIntervalDays(date, otherDate) / 365.0;
		return (double) (Math.round(k * 10) / 10.0);
	}

	/**
	 * 两个日期相差年数，用以计算年龄，返回整形
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static int getIntervalYearsInt(Date date, Date otherDate) {
		return getIntervalDays(date, otherDate) / 365;
	}

	/**
	 * 返回指定年数位移后的日期
	 */
	public static Date yearOffset(Date date, int offset) {
		return cn.hutool.core.date.DateUtil.offset(date, DateField.YEAR, offset);
	}

	/**
	 * 返回指定月数位移后的日期
	 */
	public static Date monthOffset(Date date, int offset) {
		return cn.hutool.core.date.DateUtil.offset(date, DateField.MONTH, offset);
	}

	/**
	 * 返回指定天数位移后的日期
	 */
	public static Date dayOffset(Date date, int offset) {
		return cn.hutool.core.date.DateUtil.offset(date, DateField.DAY_OF_YEAR, offset);
	}
	
	/**
	 * 返回指定秒数位移后的日期
	 */
	public static Date secondOffset(Date date, int offset) {
		return cn.hutool.core.date.DateUtil.offset(date, DateField.SECOND, offset);
	}

	/**
	 * 根据月份获得工作日
	 * 
	 * @param month
	 * @return
	 */
	public static Integer getMaxDayInMonth(Integer year, Integer month) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

}
