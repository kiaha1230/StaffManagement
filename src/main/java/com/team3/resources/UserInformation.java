package com.team3.resources;

import com.team3.model.Account;

public class UserInformation {
	private static Account ACCOUNT = null;

	public static Account getACCOUNT() {
		return ACCOUNT;
	}

	public static void setACCOUNT(Account aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	
}
