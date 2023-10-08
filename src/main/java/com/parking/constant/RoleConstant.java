package com.parking.constant;

import java.util.HashMap;
import java.util.Map;

public class RoleConstant {
	private RoleConstant() {};
	public static final String ADMIN_NAME = "ADMIN";
	public static final String MERCHANT_NAME = "MERCHANT";
	public static final String EMPLOYEE_NAME = "EMPLOYEE";
	public static final String USER_NAME = "USER";
	
	public static final Long ADMIN = 52456L;
	public static final Long MERCHANT = 40152L;
	public static final Long EMPLOYEE = 20002L;
	public static final Long USER = 30001L;
	
	 public static final Map<String, Long> roleMap = new HashMap<>();

	    static {
	        roleMap.put(ADMIN_NAME, ADMIN);
	        roleMap.put(MERCHANT_NAME, MERCHANT);
	        roleMap.put(EMPLOYEE_NAME, EMPLOYEE);
	        roleMap.put(USER_NAME, USER);
	    }
}
