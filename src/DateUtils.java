package org.i1510.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Kenshinbo@163.com 日期转换类 java.util.String <=> java.util.Date
 */
public class DateUtils {
	protected static Log logger = LogFactory.getLog(DateUtils.class);

	public static final long SECOND = 1000;

	public static final long MINUTE = SECOND * 60;

	public static final long HOUR = MINUTE * 60;

	public static final long DAY = HOUR * 24;

	public static final long WEEK = DAY * 7;

	public static final long MONTH = DAY * 30;

	public static final long YEAR = DAY * 365;

	public static final String TYPE_DATE = "date";

	public static final String TYPE_TIME = "time";

	public static final String TYPE_DATETIME = "datetime";

	/**
	 * 格式:yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式:yyyyMMddHHmmss
	 */
	public static final String PATTERN_DATETIME_ext1 = "yyyyMMddHHmmss";
	/**
	 * 格式:yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 格式:HH:mm:ss
	 */
	public static final String PATTERN_TIME = "HH:mm:ss";

	public static final String[] TYPE_ALL = { TYPE_DATE, TYPE_DATETIME,
			TYPE_TIME };

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param strDate
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static Date convertStringToDate(String strDate, String pattern) {
		if (StringUtils.isStringEmpty(strDate))
			return null;
		if (StringUtils.isStringEmpty(pattern))
			pattern = PATTERN_DATETIME;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			logger.error(strDate + "转化成" + pattern + "出现异常！");
			return null;
		}
	}

	/**
	 * 将Date转换为字符串
	 * 
	 * @param aDate
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String convertDateToString(Date date, String pattern) {
		if (date == null)
			return null;
		if (StringUtils.isStringEmpty(pattern))
			pattern = PATTERN_DATETIME;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * 将日期、时间合并成长整型数据
	 * 
	 * @param d
	 *            ，日期
	 * @param t
	 *            时间
	 * @return
	 */
	public static long getDateTimeNumber(Date d, Date t) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(t);
		c1.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
		c1.set(Calendar.MINUTE, c2.get(Calendar.MINUTE));
		c1.set(Calendar.SECOND, c2.get(Calendar.SECOND));
		c1.set(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));
		return c1.getTimeInMillis();
	}

	/**
	 * 将日期的时间部分清除后，转换成long类型
	 * 
	 * @param d
	 * @return
	 */
	public static long getDateNumber(Date d) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		return c1.getTimeInMillis();
	}

	/**
	 * 获取完整 日期+时间
	 * 
	 * @param d
	 *            以 0为今天算起 昨天为 -1 明天为 1
	 * @return
	 */
	public static Date getDate(int d) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		c1.add(Calendar.DATE, d);
		// c1.set(Calendar.HOUR_OF_DAY, 0);
		// c1.set(Calendar.MINUTE, 0);
		// c1.set(Calendar.SECOND, 0);
		// c1.set(Calendar.MILLISECOND, 0);
		return c1.getTime();
	}

	/**
	*@Author Rambo
	*@Desc：获取当前时间 yyyy-MM-dd HH:mm:ss
	*@return TODO
	*@Version  DateUtils.java,v 1.1 2013-11-15 下午2:13:28 
	*/
	public static String getNow() {
		return DateUtils.convertDateToString(DateUtils.getDate(0),
				DateUtils.PATTERN_DATETIME);
	}
	/**
	*@Author Rambo
	*@Desc：获取当前时间 yyyyMMddHHmmss
	*@return TODO
	*@Version  DateUtils.java,v 1.1 2014-9-16 上午12:49:57 
	*/
	public static String getNowExt1() {
		return DateUtils.convertDateToString(DateUtils.getDate(0),
				DateUtils.PATTERN_DATETIME_ext1);
	}

	/**
	 * @Author Rambo
	 * @Desc：获取上个月最后一天
	 * @return TODO
	 * @Version DateUtils.java,v 1.1 2013-11-15 下午2:12:33
	 */
	public static String getLastMonthDay() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int year = 0;
		int month = cal.get(Calendar.MONTH); // 上个月月份
		// int day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//起始天数
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
		System.out.println("###last month:" + month);
		if (month == 0) {
			year = cal.get(Calendar.YEAR) - 1;
			month = 12;
		} else {
			year = cal.get(Calendar.YEAR);
		}
		String endDay = year + "-" + month + "-" + day;
		return endDay + " 23:59:59";
	}
	/**
	*@Author Rambo
	*@Desc：通过毫秒获取Calendar
	*@param millis
	*@return TODO
	*@Version  DateUtils.java,v 1.1 2015-1-21 上午11:22:54 
	*/
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}

	/**
	*@Author Rambo
	*@Desc：通过日期获取Calendar
	*@param date
	*@return TODO
	*@Version  DateUtils.java,v 1.1 2015-1-21 上午11:23:07 
	*/
	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	public static void main1(String[] args) {
		System.out.println(DateUtils.getDate(0));
		System.out.println(DateUtils.getDate(-1));
		System.out.println(DateUtils.convertDateToString(DateUtils.getDate(-1),
				DateUtils.PATTERN_DATETIME));
		System.out.println(DateUtils.convertStringToDate("2010-12-14 10:10:10",
				DateUtils.PATTERN_DATETIME));
		System.out.println("********************************");
		System.out.println(DateUtils.convertStringToDate("2010-12-14 ",
				"yyyy-MM-dd"));
		System.out.println(DateUtils.getNow());
		logger.debug("=====");
		logger.info("**********");
		logger.error("--------------");
	}
	public static void main(String[] args) {
		Date local1970 = DateUtils.convertStringToDate("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时区的格林尼治时间的毫秒数:"+local1970.getTime());
//		TimeZone timeBJ = TimeZone.getTimeZone("PRC");//得到北京时间的时区
		TimeZone timeBJ = TimeZone.getTimeZone("GMT+8");//得到北京时间的时区	
		String[] strs = timeBJ.getAvailableIDs();
		for (int i = 0; i < strs.length; i++) {
			System.out.println("东八区ID:"+strs[i]);
		}
		System.out.println(timeBJ.getOffset(local1970.getTime()));
		//所以如果要获取当地正确的时间
		TimeZone timeLocal = TimeZone.getTimeZone(getCalendar(local1970).getTimeZone().getID());//得到当前时间的时区	
		long timeZoneMi= timeLocal.getOffset(local1970.getTime());//当前时间要扣减的毫秒数
		long date1970 = local1970.getTime() + timeZoneMi;
		System.out.println("1970年格林威治时间的毫秒数:"+date1970);
	}

}
