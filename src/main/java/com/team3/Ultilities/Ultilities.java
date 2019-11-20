package com.team3.Ultilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultilities {
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String strDate = sdf.format(date);
		return strDate;
	}

	public static Date stringToDate(String dateString) {
		Date date = new Date();
		return date;
	}
	 public static void getLastDot(String start) {
	        String [] returnStr =  start.split("\\.");
	        System.out.println(returnStr[returnStr.length-1]);
	    }

}
