/*
 * @(#)DateUtil.java 2014-1-18
 *
 */
package com.ddt.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * DateUtil.java
 * 
 * @author <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version 1.0 2014-1-18
 * @since 1.0
 */
public class DateUtils {
	
	private static Logger logger = Logger.getLogger(DateUtils.class);
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATETIMEFORMAT = "yyyyMMddHHmmss";

	/**
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDateToString(String format, Date date) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isBlank(format)) {
			format = DATE_TIME_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @param format
	 * @param time
	 * @return
	 */
	public static String parseLongToString(String format, long time) {
		SimpleDateFormat mydate = new SimpleDateFormat(format);
		return mydate.format(new Date(time));
	}
	
	/**
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static long parseStringToTime(String format, String time) {
		SimpleDateFormat mydate = new SimpleDateFormat(format);
		try {
			Date date = mydate.parse(time);
			if (date != null) {
				return date.getTime();
			}
		} catch (ParseException e) {
			logger.error(e);
		}
		return 0;
	}
	
	/**
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static Date parseStringToDate(String format, String time) {
		SimpleDateFormat mydate = new SimpleDateFormat(format);
		try {
			Date date = mydate.parse(time);
			if (date != null) {
				return date;
			}
		} catch (ParseException e) {
			logger.error(e);
		}
		return null;
	}
}
