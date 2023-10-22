package com.parking.utils;

public class EmailUtils {
	
	
	public static String hide(String email)
	{
		
		int index = email.indexOf("@");
		
		String name = email.substring(0, index);
		
		int n =name.length();
		
		String symbol = "*";
		
		String hidddenEmail = name.charAt(0) + symbol.repeat(n-3) + name.substring(n-2, n) + email.substring(index,email.length());
		
		return hidddenEmail;
	}
}
