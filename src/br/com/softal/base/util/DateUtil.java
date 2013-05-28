package br.com.softal.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String DATE_PATTERN = "dd/MM/yyyy"; 
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy hh:mm:ss"; 
	
	public static String dateToStr(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat( DateUtil.DATE_PATTERN );
		return sdf.format(data);
	}
	
	public static String dateTimeToStr(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat( DateUtil.DATE_TIME_PATTERN );
		return sdf.format(data);
	}
	
	public static Date strToDate(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( DateUtil.DATE_PATTERN );
		return sdf.parse(data);
	}

}
