package com.coupon.api.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *Created by chengliang on 2018/11/27.
 */
public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);
	public static final String TIME_PATTERN = "HH:mm:ss";
	public static final String DATE_TIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_PATTERN1 = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_YYYYMM = "yyyy-MM";
	public static final String DATE_YYYYMMDD2 = "YYYYMMDD";
	public static final String DATE_HHMMSS = "HH:mm:ss";
	public static final String DATE_HH_MM_SS = "HHmmss";
	public final static String YYYYMMDD = "yyyyMMdd";
	public static final String DATE_YYYYMMDD_PATTERN = "yyyyMMdd";
	public static final String TIME_HHMM_PATTERN = "HH:mm";
	public static final String TIME_HHMM_PATTERN2 = "HHmm";
	public static final String DATE_TIME_NO_HORI_PATTERN = "yyyyMMdd HH:mm:ss";
	public static final String DATE_TIME_NO_SPACE_PATTERN = "yyyyMMddHHmmss";
	public static final String DATE_TIME_PLAYBILL_PATTERN = "yyyyMMdd HH:mm";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_ENGLISH_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
	public static final String DATE_YYYY_MM_DD_PATTERN = "yyyy-MM-dd";


	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(DATE_TIME_MS_PATTERN);
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
	public static final SimpleDateFormat dateFormatNew = new SimpleDateFormat(DATE_TIME_PATTERN1);
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(DATE_YYYYMMDD_PATTERN);
	public static final SimpleDateFormat yyyyMMdd1 = new SimpleDateFormat(DATE_YYYYMMDD);
	public static final SimpleDateFormat hhmmss = new SimpleDateFormat(DATE_HHMMSS);

	public static final SimpleDateFormat HHmm = new SimpleDateFormat(TIME_HHMM_PATTERN);
	public static final SimpleDateFormat HHmm2 = new SimpleDateFormat(TIME_HHMM_PATTERN2);
	public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(DATE_TIME_NO_HORI_PATTERN);
	public static final SimpleDateFormat yyyyMMddHHmmssFile = new SimpleDateFormat(DATE_TIME_NO_SPACE_PATTERN);
	public static final SimpleDateFormat PLAYBILL_TIME_PATTERN = new SimpleDateFormat(DATE_TIME_PLAYBILL_PATTERN);
	public static final SimpleDateFormat ENGLISH_SDF = new SimpleDateFormat(DATE_ENGLISH_FORMAT, Locale.ENGLISH);
	public static final SimpleDateFormat dateYYYYMMDD = new SimpleDateFormat(DATE_YYYY_MM_DD_PATTERN);


	private static Map<String, SimpleDateFormat> patternFormatMap;

	public static Map<String, SimpleDateFormat> getInstance() {

		if (patternFormatMap == null) {

			patternFormatMap = new HashMap<String, SimpleDateFormat>();
			patternFormatMap.put(DATE_TIME_MS_PATTERN, timeFormat);
			patternFormatMap.put(DATE_TIME_PATTERN, dateFormat);
			patternFormatMap.put(DATE_YYYYMMDD_PATTERN, yyyyMMdd);
			patternFormatMap.put(DATE_YYYYMMDD, yyyyMMdd1);
			patternFormatMap.put(DATE_HHMMSS, hhmmss);
			patternFormatMap.put(TIME_HHMM_PATTERN, HHmm);
			patternFormatMap.put(TIME_HHMM_PATTERN2, HHmm2);
			patternFormatMap.put(DATE_TIME_NO_HORI_PATTERN, yyyyMMddHHmmss);
			patternFormatMap.put(DATE_TIME_NO_SPACE_PATTERN, yyyyMMddHHmmssFile);
			patternFormatMap.put(DATE_TIME_PLAYBILL_PATTERN, PLAYBILL_TIME_PATTERN);
			patternFormatMap.put(DATE_ENGLISH_FORMAT, ENGLISH_SDF);

		}
		return patternFormatMap;

	}


	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}


	/**
	 * 是否是最后一天
	 *
	 * @return
	 */
	public static boolean isLastDayOfMonth() {
		boolean flag = false;
		String date = formatDate(DATE_YYYYMMDD_PATTERN, new Date());
		if (StringUtils.isNotBlank(date) &&
				StringUtils.isNotBlank(getMaxMonthDate())
				&& StringUtils.equals(date, getMaxMonthDate())) { // getMaxMonthDate().equals(getNowTime())
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取当前月份最后一天
	 *
	 * @return
	 */
	public static String getMaxMonthDate() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 获取当前月份
	 *
	 * @return
	 */
	public static String getNowMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return month + "";
	}

	/**
	 * 获取上个月
	 *
	 * @return
	 */
	public static String getLastMonth() {
		String nowDate = formatDate(DateUtil.DATE_YYYYMM, new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(nowDate + "-" + "01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);

	/*	String lastDate = c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1);*/
		String month = c.get(Calendar.MONTH) + 1 + "";
		return month;
	}


	/**
	 * 获取当前时间前后N月的最后一天
	 *
	 * @return
	 */
	public static String getLastMaxMonthDate(String pattern, Integer n) {
		SimpleDateFormat dft = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, n);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dft.format(calendar.getTime());
	}

	/**
	 * 时间类型转换   如：yyyyMMdd转yyyy-MM-dd
	 *
	 * @param sourceFormat 传人类型，如：yyyyMMdd
	 * @param targetFormat 目标类型，如：yyyy-MM-dd
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static String formatToFormat(String sourceFormat, String targetFormat, String dateString) throws ParseException {
		SimpleDateFormat sdfSource = new SimpleDateFormat(sourceFormat);
		SimpleDateFormat sdfTarget = new SimpleDateFormat(targetFormat);
		return sdfTarget.format(sdfSource.parse(dateString));
	}


	public static String formatDate(String pattern, Date adate) {

		SimpleDateFormat sdf = DateUtil.getInstance().get(pattern);

		if (sdf == null) {

			sdf = new SimpleDateFormat(pattern);
			DateUtil.getInstance().put(pattern, sdf);
		}

		return sdf.format(adate);
	}


	public static Date parseDate(String pattern, String dateStr) {

		SimpleDateFormat sdf = DateUtil.getInstance().get(pattern);

		if (sdf == null) {

			sdf = new SimpleDateFormat(pattern);
			DateUtil.getInstance().put(pattern, sdf);
		}

		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Date parseDate2(String source, String pattern) {
		if (source == null || pattern == null) {
			return null;
		}
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 计算时间的起始时间
	 */
	private final static String BASIC_DATE = "2000-01-01 00:00:00";

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * 把日期字符串yyyy-MM-dd HH:mm:ss转换成HH:mm形式
	 */
	public static String strToString(String date) {
		if (date == null || "".equals(date)) {
			return date;
		}
		String temp = "";
		try {
			Date dateStr = dateFormat.parse(date);
			temp = HHmm.format(dateStr);
		} catch (Exception ex) {
			log.debug(ex.getStackTrace());
		}
		return temp;
	}

	/**
	 * 把日期字符串yyyy-MM-ddTHH:mm:ss转换成yyyy-MM-dd形式
	 */
	public static String strToStringNew(String date) {
		if (date == null || "".equals(date)) {
			return "";
		}
		String temp = "";
		try {
			Date dateStr = dateFormatNew.parse(date);
			temp = dateYYYYMMDD.format(dateStr);
		} catch (Exception ex) {
			log.debug(ex.getStackTrace());
		}
		return temp;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat df;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(DATE_FORMAT);
			returnValue = df.format(date);
		}

		return (returnValue);
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle("ApplicationResources", locale)
					.getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "yyyy-MM-dd";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask   the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException when String doesn't match the expected format
	 * @see SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			//log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * @see SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate) {
		Date aDate = null;
		if (strDate == null) {
			return null;
		}
		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
		}
		return aDate;
	}

	public static java.sql.Date convertDateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Timestamp convertDateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}


	public static String getNowTime(Date date) {
		if (date == null) {
			return "";
		}
		return timeFormat.format(date);
	}

	public static String getDateTime(String sdate) {
		try {
			Timestamp date = stringToTimestamp(sdate);
			return dateFormat.format(date);
		} catch (Exception e) {
			return sdate;
		}
	}

	public static Timestamp stringToTimestamp(String timestampStr) {
		if (timestampStr == null || timestampStr.length() < 1)
			return null;
		return Timestamp.valueOf(timestampStr);
	}

	/**
	 * 根据日期计算出所在周的日期，并返回大小为7的数组
	 *
	 * @param date
	 * @return
	 */
	public static String[] getWholeWeekByDate(Date date) {
		String[] ss = new String[7];
		Calendar calendar = Calendar.getInstance();
		for (int i = 0, j = 2; i < 6 && j < 8; i++, j++) {
			calendar.setTime(date);
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.set(Calendar.DAY_OF_WEEK, j);
			ss[i] = getFormatDate(calendar.getTime());
		}
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
		ss[6] = getFormatDate(calendar.getTime());
		return ss;
	}

	/**
	 * 返回格式 yyyyMMdd的日期格式
	 *
	 * @param d
	 * @return
	 */
	public static String getFormatDate(Date d) {
		return yyyyMMdd.format(d);
	}

	public static String getHHmm2(Date d) {
		return HHmm2.format(d);
	}

	public static Date getDateByString(String pattern) throws ParseException {
		return yyyyMMdd.parse(pattern);
	}

	public static Date getPlayBillTimeByPattern(String date) throws ParseException {
		return PLAYBILL_TIME_PATTERN.parse(date);
	}


	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String nowTime = df.format(date);
		return nowTime;
	}

	/**
	 * @return 当前标准日期yyyyMMddHHmmss
	 */
	public static String getNowTimeNumber() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String nowTime = df.format(date);
		return nowTime;
	}

	/**
	 * 获取从2000年1月1日 00:00:00开始到指定日期的秒数
	 *
	 * @param date 日期
	 * @return long
	 */
	public static Long getSeconds(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date basicDate = formatter.parse(BASIC_DATE, new ParsePosition(0));
		long secLong = (date.getTime() - basicDate.getTime()) / 1000L;
		return secLong;
	}

	/**
	 * 获取从2000年1月1日 00:00:00开始到指定日期的秒数
	 *
	 * @param df      日期
	 * @param dateStr 日期格式
	 *                例如：yyyy-MM-dd HH:mm:ss
	 * @return long
	 */
	public static Long getSeconds(String dateStr, String df) {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		if (df == null || "".equals(df)) {
			df = DATE_FORMAT;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(df);
		Date date = formatter.parse(dateStr, new ParsePosition(0));
		return getSeconds(date);
	}

	/**
	 * 返回格式 yyyyMMdd的日期格式
	 *
	 * @return
	 */

	public static Date getDateByStringyyyyMMddHHmmss(String pattern) throws ParseException {
		return yyyyMMddHHmmssFile.parse(pattern);
	}


	/**
	 * 获取相差几秒
	 *
	 * @param first
	 * @param secend
	 * @return
	 */
	public static int minusSecond(Date first, Date secend) {
		return (int) Math.ceil((first.getTime() - secend.getTime()) / 1000.0);
	}

	public static Date getFormatDateByEnglishSDF(String s) {
		try {
			return ENGLISH_SDF.parse(s);
		} catch (ParseException e) {
			log.error("", e);
			return null;
		}
	}

	public static String getFormatDateByyyyyMMddHHmmssFile(Date d) {
		return yyyyMMddHHmmssFile.format(d);
	}

	public static String formateStrDate(String d) {
		Date formateDate = null;
		try {
			formateDate = dateFormat.parse(d);
			String dateStr = getFormatDateByyyyyMMddHHmmssFile(formateDate);
			return dateStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getMaxDate(Date dt) {
		Date ret = null;
		if (dt != null) {
			ret = parseDate(DATE_TIME_PATTERN, formatDate("yyyy-MM-dd 23:59:59", dt));
		}
		return ret;
	}

	public static Date getMinDate(Date dt) {
		Date ret = null;
		if (dt != null) {
			ret = parseDate(DATE_TIME_PATTERN, formatDate("yyyy-MM-dd 00:00:00", dt));
		}
		return ret;
	}

	/**
	 *
	 * @param dateTime
	 * @return 指定时间与当时时间相差毫秒值
	 * @throws ParseException
	 */
	public static long getMilliDifference(String dateTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//给定的时间
		Date d = format.parse(dateTime);

		//当前时间处理
		Calendar cal = Calendar.getInstance();

		//给定时间处理
		Calendar setCal = Calendar.getInstance();
		setCal.setTime(d);
		long dayDiff = (cal.getTimeInMillis() - setCal.getTimeInMillis());
		return dayDiff;

	}

	/**
	 *
	 * @param dateTime
	 * @return 指定时间与当时时间相差毫秒值
	 * @throws ParseException
	 */
	public static long getMilliDifference(Date dateTime) throws ParseException {
		//给定的时间
		Date d = dateTime;

		//当前时间处理
		Calendar cal = Calendar.getInstance();

		//给定时间处理
		Calendar setCal = Calendar.getInstance();
		setCal.setTime(d);
		long dayDiff = (cal.getTimeInMillis() - setCal.getTimeInMillis());
		return dayDiff;

	}

	/**
	 *
	 * @param dateTime
	 * @return 指定时间与当时时间相差天数
	 * @throws ParseException
	 */
	public static long getDateDifference(String dateTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//给定的时间
		Date d = format.parse(dateTime);

		//当前时间处理
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		//给定时间处理
		Calendar setCal = Calendar.getInstance();
		setCal.setTime(d);
		setCal.set(Calendar.HOUR_OF_DAY, 0);
		setCal.set(Calendar.MINUTE, 0);
		setCal.set(Calendar.SECOND, 0);
		setCal.set(Calendar.MILLISECOND, 0);

		long dayDiff = (cal.getTimeInMillis() - setCal.getTimeInMillis());
		return dayDiff;

	}

	public static String addOneday(String today) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = new Date(f.parse(today).getTime() + 24 * 3600 * 1000);
			return f.format(d);
		} catch (Exception ex) {
			return "输入格式错误";
		}
	}

	public static Date addOneDayNew(String today) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = new Date(f.parse(today).getTime() + 24 * 3600 * 1000);
			return d;
		} catch (Exception ex) {
			return null;
		}
	}

	// 当前日期前几天或者后几天的日期
	public static Date afterNDay(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		return d2;
	}


	public static String convertTime(String time) {
		StringBuffer sb = new StringBuffer();
		sb.append(time.substring(0, 4)).append("-").append(time.substring(4, 6)).append("-").append(time.substring(6));
		return sb.toString();
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}


	/**
	 * 获取过去第几天的日期
	 *
	 * @param day
	 * @return
	 */
	public static String getPastDate(int day, String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 day 天的日期
	 *
	 * @param day
	 * @return
	 */
	public static String getFetureDate(int day, String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String result = format.format(today);
		return result;
	}

	/**
	 * 两个时间之间相差距离多少天
	 * @return 相差天数
	 */
	/**
	 * 两个时间之间相差距离多少天
	 *
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1, String str2, String formatStr) throws Exception {
		DateFormat df = new SimpleDateFormat(formatStr);
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}


	/**
	 * 增加天
	 *
	 * @param time
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date time, int amount) {
		return DateUtils.addDays(time, amount);
	}


	/**
	 * @param
	 * @return 在当前时间上增加小时
	 * @Author:chengliang
	 * @date 2018/11/28_11:32
	 */
	public static Date addHour(Date targetDate, int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(targetDate);
		cal.add(Calendar.HOUR, hour);
		Date time = cal.getTime();
		return time;
	}

	/**
	 * @param
	 * @return long
	 * 日期转换为10位时间戳
	 * @Author:chengliang
	 * @date 2019/1/30_13:53
	 */
	public static Integer date2long(Date date) {
		return (int) (date.getTime() / 1000);
	}

	/**
	 * Date类型转换为10位时间戳
	 * @param time
	 * @return
	 */
	public static Integer DateToTimestamp(Date time) {
		Timestamp ts = new Timestamp(time.getTime());
		return (int) ((ts.getTime()) / 1000);
	}
	
	/**
	 *  将10位时间戳转换为时间
	 * @param time
	 * @return
	 */
	public static String w102yyyyMMdd(Integer time){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(time+"000");
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
}
