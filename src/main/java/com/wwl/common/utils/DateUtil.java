package com.wwl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {
	
private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	
	public static final String DEFAULT_PATTERN_DAY = "yyyy-MM-dd";
	public static final String DEFAULT_PATTERN_DAY_TIME = "yyyy-MM-dd HH:mm:ss";
	

	private static final Object object = new Object();
	
	public static Integer getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	public static Integer getCurrentMonth(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH)+1;
	}

	public static String getCurrentDateStr() {
		Date date = new Date();
		return DateToString(date, DEFAULT_PATTERN_DAY);
	}
	
	public static Date getCurrentDate() {
		Date date = new Date();
		String str = DateToString(date, DEFAULT_PATTERN_DAY);
		return StringToDate(str);
	}
	
	public static String getCurrentDateTimeStr() {
		Date date = new Date();
		return DateToString(date, DEFAULT_PATTERN_DAY_TIME);
	}

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param pattern
	 * @return
	 * @throws RuntimeException
	 */
	private static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
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
		return DateToString(date , DEFAULT_PATTERN_DAY);
	}
	
	public static String DateTimeToString(Date date) {
		return DateToString(date , DEFAULT_PATTERN_DAY_TIME);
	}

	/**
	 * date转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String DateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
			}
		}
		return dateString;
	}

	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date, DEFAULT_PATTERN_DAY),
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	public static double getIntervalYears(String date, String otherDate) {
		return getIntervalYears(StringToDate(date , DEFAULT_PATTERN_DAY),
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	public static int getIntervalYearsInt(String date, String otherDate) {
		return getIntervalYearsInt(StringToDate(date, DEFAULT_PATTERN_DAY),
				StringToDate(otherDate, DEFAULT_PATTERN_DAY));
	}

	/**
	 * 计算相差天数
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		int num = 0;
		Date dateTmp = StringToDate(DateToString(date, DEFAULT_PATTERN_DAY),
				DEFAULT_PATTERN_DAY);
		Date otherDateTmp = StringToDate(DateToString(otherDate, DEFAULT_PATTERN_DAY),
				DEFAULT_PATTERN_DAY);
		if (dateTmp != null && otherDateTmp != null) {
			long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}

	/**
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
	 * 用以计算年龄，返回整形
	 * 
	 * @param date
	 * @param otherDate
	 * @return
	 */
	public static int getIntervalYearsInt(Date date, Date otherDate) {
		return getIntervalDays(date, otherDate) / 365;
	}

	// /////////////n天前，后； n月前后等等
	
	/**
	  * 返回指定年数位移后的日期
	  */
	 public static Date yearOffset(Date date, int offset) {
	  return offsetDate(date, Calendar.YEAR, offset);
	 }

	 /**
	  * 返回指定月数位移后的日期
	  */
	 public static Date monthOffset(Date date, int offset) {
	  return offsetDate(date, Calendar.MONTH, offset);
	 }
	 
	 /**
	  * 返回指定天数位移后的日期
	  */
	 public static Date dayOffset(Date date, int offset) {
	  return offsetDate(date, Calendar.DATE, offset);
	 }

	/**
	 * 返回指定日期相应位移后的日期
	 * 
	 * @param date
	 *            参考日期
	 * @param field
	 *            位移单位，见 {@link Calendar}
	 * @param offset
	 *            位移数量，正数表示之后的时间，负数表示之前的时间
	 * @return 位移后的日期
	 */
	public static Date offsetDate(Date date, int field, int offset) {
		Calendar calendar = convert(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	private static Calendar convert(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 *   根据月份获得工作日
	 * @param month
	 * @return
	 */
	public static Integer getMaxDayInMonth(Integer year , Integer month){
		Calendar c = Calendar.getInstance();	
		c.set(year, month, 0);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

}
