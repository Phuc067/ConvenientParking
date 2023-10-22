package com.parking.utils;

public class AddressUtils {

	public static String formart(String address)
	{
		
		address = address.replaceAll("[,./;'\\[\\]!~@#$`\\$^&*()]", "");
		address = address.trim();
		address = address.replaceAll("\\s+", " ");
		address = address.toLowerCase();
		return address;
	}
}
