package com.neueda.urlshortener.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date getCurrentDate(){
		return Calendar.getInstance().getTime();
		
	}
	
	public static Date getOneDayBefore(){
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	public static Date getOneWeekBefore(){
		Calendar cal = Calendar.getInstance();		
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	
}
