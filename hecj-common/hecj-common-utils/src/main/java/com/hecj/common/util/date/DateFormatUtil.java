package com.hecj.common.util.date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @类功能说明：格式化工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 下午02:59:54
 * @版本：V1.0
 */
public final class DateFormatUtil {
	
	private static final SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSS");

	/**
	 * @函数功能说明 当前系统时间
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容
	 * @参数： @return    
	 * @return Date   
	 * @throws
	 */
	public static Date getCurrDate(){
		return new Date();
	}
	
	/**
	 * @格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @格式化转换
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateStr, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 当前时间字符串
	 */
	public static String getCurrTimeStr(){
		return format.format(new Date());
	}
	
	/**
	 * 描述：当天开始时间
	 * @author: hecj
	 */
	public static Date getDayBegin(Date date){
		return parse(format(date, "yyyy-MM-dd"),"yyyy-MM-dd");
	}
	
	/**
	 * 描述：当天结束时间
	 * @author: hecj
	 */
	public static Date getDayEnd(Date date){
		return new Date(getDayBegin(date).getTime()+24*60*60*1000-1);
	}
	
	/**
     * 当月第一天
     * @return
     */
    public static String getMonthFirstDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;

    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getMonthLastDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String s = df.format(ca.getTime());
        return s;
    }
    
    /**
     * 描述：时间转文本备注
     * @author: hecj
     */
    public static String date2Text(Date date){
    	long intervalMin= (getCurrDate().getTime()-date.getTime())/1000/60;
    	if(intervalMin >= 100*365*24*60){
    		return "年代久远";
    	}else if(intervalMin >= 365*24*60){
    		return (intervalMin/(365*24*60))+"年前";
    	} else if(intervalMin >= 30*24*60){
    		return (intervalMin/(30*24*60))+"个月前";
    	} else if(intervalMin >=24*60){
    		return (intervalMin/(24*60))+"天前";
    	} else if(intervalMin >=60){
    		return (intervalMin/60)+"小时前";
    	} else if(intervalMin >=5){
    		return "5分钟前";
    	} else if(intervalMin >=2){
    		return "2分钟前";
    	} else{
    		return "刚刚";
    	}
    }
    
    /**
	 * 获得当前时间的毫秒数
	 * <p>
	 * 详见{@link System#currentTimeMillis()}
	 * 
	 * @return
	 */
	public static long millis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * 获得当前Chinese月份
	 * 
	 * @return
	 */
	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得月份中的第几天
	 * 
	 * @return
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 今天是星期的第几天
	 * 
	 * @return
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是年中的第几天
	 * 
	 * @return
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 *判断原日期是否在目标日期之前
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 *判断原日期是否在目标日期之后
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/**
	 *判断两日期是否相同
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 * 
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 获得当前月的最后一天
	 * <p>
	 * HH:mm:ss为0，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}
	

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 获得周五日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 获得周六日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 获得周日日期
	 * <p>
	 * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * 
	 * @return
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}
    
}