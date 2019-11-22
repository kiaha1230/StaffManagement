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

	public static String getColName(String start) {
		String start1 = start.substring(1);
		String[] split = start1.split("(?=\\p{Upper})");
		String returnStr = "";
		for (int i = 0; i <= split.length - 1; i++) {
			if (i == split.length - 1) {
				returnStr += split[i].toUpperCase();
				break;
			}
			returnStr += split[i].toUpperCase() + "_";
		}
		return returnStr;

	}
	public static String getColNameWithoutEC(String start) {
		String[] split = start.split("(?=\\p{Upper})");
		String returnStr = "";
		for (int i = 0; i <= split.length - 1; i++) {
			if (i == split.length - 1) {
				returnStr += split[i].toUpperCase();
				break;
			}
			returnStr += split[i].toUpperCase() + "_";
		}
		return returnStr;

	}

}
