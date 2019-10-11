/**
 * 
 */
package com.dongyu.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @作者  董宇
 *	日期处理工具
 * @时间 :2019年10月11日
 */
public class DateUtil {
	public static final String DATE_FORMAT_TYPE="yyyy-MM-dd";

	public static final String TIME_FORMAT_TYPE="yyyy-MM-dd HH:mm:ss";
	private DateUtil() {
	}
	/*
	 * 	字符串转成日期
	 * 
	 * */
	public static Date changeStringToDate(String source,String dateFormatType) {
		SimpleDateFormat sdFormat = new SimpleDateFormat(dateFormatType);
		Date date = null;
		try {
			date = sdFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}
	
	/*
	 * 	日期专字符串
	 * 
	 * */
	public static String dateToString(Date date,String dateFormatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatType);
		String dateString = formatter.format(date);
		return dateString;
	}
	//根据生日与当前系统进行比对
	public static int getAgeByBirthday(Date birthday) {
		// 生日晚于当前日期就不做计算
		Calendar calendar = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(birthday);
		if (calendar.before(c)) {
			return 0;
		}
		// 获取当前年、月、日
		int sysYear = calendar.get(Calendar.YEAR);
		int sysMonth = calendar.get(Calendar.MONTH) + 1;
		int sysDate = calendar.get(Calendar.DATE);
		// 获取生日
		int myYear = c.get(Calendar.YEAR);
		int myMonth = c.get(Calendar.MONTH) + 1;
		int myDate = c.get(Calendar.DATE);
		// 系统年-生日年，生日月份日期计算周岁
		int age = sysYear - myYear;
		// 生日月份大于当前月份,年龄减1
		if (sysMonth < myMonth) {
			return age--;
		} else if (sysMonth == myMonth) {
			// 当当前月份等于生日月份，再看日期，今天小于生日，年龄减1
			if (sysDate < myDate) {
				return age--;
			}
		}
		return age;
	}
	/**
	 * 
	 * Date类的gettime方法返回当前对象一个long值 （单位毫秒） 思路： 1，分别计算两个对象的long值。 2，再用long值想减。
	 * 3，用相减的毫秒换算成天数。
	 *
	 */
	public static int getFuture(Date future) {
		Date now = new Date();
		if (now.after(future)) {
			System.out.println("未来日期不能小于当前日期");
			return 0;
		}
		long nowTime = now.getTime();
		long futureTime = future.getTime();
		// 一天24*3600
		int day = (int) (((futureTime - nowTime) / 1000) / (24 * 3600));

		return day;

	}

	/**
	 * 
	 * @Title: getFuture
	 * @Description: 求未来日期离今天还剩的天数
	 * @param future
	 * @return
	 * @return int
	 */
	public static int getFuture(Calendar future) {
		Calendar now = Calendar.getInstance();
		if (now.after(future)) {
			System.out.println("未来日期不能小于当前日期");
			return 0;
		}
		long nowTime = now.getTimeInMillis();
		long futureTime = future.getTimeInMillis();
		// 一天24*3600
		int day = (int) (((futureTime - nowTime) / 1000) / (24 * 3600));

		return day;

	}

	/**
	 * 
	 * @Title: getBefore
	 * @Description: 求过去日期离今天还剩的天数
	 * @param before
	 * @return
	 * @return int
	 */
	public static int getBefore(Date before) {
		Date now = new Date();
		if (now.before(before)) {
			System.out.println("过去日期不能大于当前日期");
			return 0;
		}
		long nowTime = now.getTime();
		long beforeTime = before.getTime();
		// 一天24*3600
		int day = (int) (((nowTime - beforeTime) / 1000) / (24 * 3600));

		return day;

	}
	/**
	 * 
	* @Title: decideIsToday
	* @Description: 判断给定的日期是否为今天
	* @param calendar
	* @return
	* @return boolean
	 */
	public static boolean decideIsToday(Calendar calendar){
		//获取当前的日期
		Calendar c = Calendar.getInstance();
		//获取到日期的天数
		int today = c.get(Calendar.DAY_OF_YEAR);
		System.out.println(today);
		//获取到指定日期的天数
		int thisDay = calendar.get(Calendar.DAY_OF_YEAR);
		//判断是否相等，不相等就表示不是今天，false
		return (today == thisDay);
		
	}
	/**
	 * 
	 * @Title: getBefore
	 * @Description: 求过去日期离今天还剩的天数
	 * @param before
	 * @return
	 * @return int
	 */
	public static int getBefore(Calendar before) {
		Calendar now = Calendar.getInstance();
		if (now.before(before)) {
			System.out.println("过去日期不能大于当前日期");
			return 0;
		}
		long nowTime = now.getTimeInMillis();
		long beforeTime = before.getTimeInMillis();
		// 一天24*3600
		int day = (int) (((nowTime - beforeTime) / 1000) / (24 * 3600));

		return day;

	}

	/**
	 * 
	 * @Title: isSameWeek
	 * @Description: 判断给定的日期是否在本周之内 提示：判定当前日期为周几，注意周日为1，周六为7。然后计算出本周日期范围
	 * @param today
	 * @return
	 * @return boolean
	 */
	public static boolean isSameWeek(Calendar today) {
		Calendar now = Calendar.getInstance();
		int a = now.get(Calendar.WEEK_OF_MONTH);
		System.out.println(a);
		int b = today.get(Calendar.WEEK_OF_MONTH);
		System.out.println(b);
		if (a == b) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: isSameMonth
	 * @Description: 判断给定的日期是否在本月之内
	 * @param today
	 * @return
	 * @return boolean
	 */
	public static boolean isSameMonth(Calendar today) {
		Calendar now = Calendar.getInstance();
		int a = now.get(Calendar.MONTH);
		System.out.println(a);
		int b = today.get(Calendar.MONTH);
		System.out.println(b);
		if (a == b) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: initFirstDayOfThisMonth
	 * @Description: 给定时间对象，初始化到该月初的1月1日0时0分0秒0毫秒
	 * @param day
	 * @return void
	 */
	public static void initFirstDayOfThisMonth(Calendar day) {
		//当前月的第一天
		day.set(Calendar.DAY_OF_MONTH, 1);
		//
		day.set(Calendar.HOUR, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
		System.out.println(day.getTime());
	}
	/**
	 * 
	* @Title: initFirstDayOfThisMonth
	* @Description: 根据传入的参数获取该日期的月初日期
	* @param day
	* @return void
	 */
	public static Date getDateByMonthInit (Date day) {
		String str =dateToString(day, TIME_FORMAT_TYPE);
		str = str.split(" ")[0].substring(0,8)+"01 00:00:00";
		Date date = changeStringToDate(str, TIME_FORMAT_TYPE);
		System.out.println(str);
		return date;
	}

	/**
	 * 
	 * @Title: getLastDayOfThisMonth
	 * @Description: 给定时间对象，设定到该月最后一天的23时59分59秒999毫秒
	 * @param target
	 * @return void
	 */
	public static void getLastDayOfThisMonth(Calendar target) {
		// 获取当月最后一天
		target.set(Calendar.DAY_OF_MONTH, target.getActualMaximum(Calendar.DAY_OF_MONTH));
		//小时赋值
		target.set(Calendar.HOUR, 23);
		//分钟赋值
		target.set(Calendar.MINUTE, 59);
		//秒钟赋值
		target.set(Calendar.SECOND, 59);
		//毫秒赋值
		target.set(Calendar.MILLISECOND, 999);
		System.out.println(target.getTime());
	}
	
	public static Date getDateByMonthLast(Date target) {
		// 获取当月最后一天
		String str =dateToString(target, TIME_FORMAT_TYPE);
		str = str.split(" ")[0].substring(0,8)+"30 23:59:59";
		Date date = changeStringToDate(str, TIME_FORMAT_TYPE);
		System.out.println(str);
		return date;
	}
	
	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TYPE);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: compareToDate
	 * @Description: 时间比较:时间相等，结果为0;date1<date2，结果为-1;date1>date2，结果为1
	 * @param date1
	 * @param date2
	 * @return
	 * @return int
	 */
	public static int compareToDate(Date date1, Date date2) {
		int result = date1.compareTo(date2);
		return result;

	}

	 /**
	  * 
	  * 判断是否是闰年
	  * @param     整型 year
	  * @return    返回 boolean
	  * @exception 异常描述
	  */
	 public  static boolean  isLeap(int year){
	  //如果是闰年返回true，否则为false
	        if(year%4==0 && year%100!=0 ||year%400==0){
	              return true;
	         }
	        else{
	              return false;
	        }
	     }
	public static void main(String[] args) {
		System.out.println(getDateByMonthLast(new Date()));
	}
}

