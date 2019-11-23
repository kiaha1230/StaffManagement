package com.team3.Ultilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultilities {
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		String strDate = sdf.format(date);
		return strDate;
	}

	public static String dateToStringUSFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		return strDate;
	}

	public static Date stringToDate(String dateString) {
		Date date = new Date();
		return date;
	}

	public static String getStringTimeFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String strDate = sdf.format(date);
		return strDate;
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

	public static Double getPercentSalGrade(Double amount) {
		if (amount <= 5000000) {
			return 0.05;
		} else if (amount > 5000000 && amount <= 10000000) {
			return 0.1;
		} else if (amount > 10000000 && amount <= 18000000) {
			return 0.15;
		} else if (amount > 18000000 && amount <= 32000000) {
			return 0.2;
		} else if (amount > 32000000 && amount <= 52000000) {
			return 0.25;
		} else if (amount > 52000000 && amount <= 80000000) {
			return 0.3;
		} else {
			return 0.35;
		}
	}

}
