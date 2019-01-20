package cn.hcw.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private DateUtils(){}
	
	public static final String DATE_FORMAT_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	public static Date time(){
		return Calendar.getInstance().getTime();
	}
}
