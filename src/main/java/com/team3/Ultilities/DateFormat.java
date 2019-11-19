package com.team3.Ultilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String strDate = sdf.format(date);
		return strDate;
	}

	public static Date stringToDate(String dateString) {
		Date date = new Date();
		return date;
	}

}
