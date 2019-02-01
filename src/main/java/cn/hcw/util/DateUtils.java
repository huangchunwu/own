package cn.hcw.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private DateUtils(){}
	
	public static final String DATE_FORMAT_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	public static Date time(){
		return Calendar.getInstance().getTime();
	}


	private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static String formatDate(Date date) {
		return format1.get().format(date);
	}
}
