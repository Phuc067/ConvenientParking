package com.parking.model;

import java.util.HashMap;
import java.util.Map;

import com.parking.constant.RoleConstant;

public class RoleMap {
	public static Map<String, Long> roleMap = new HashMap<String, Long>();
	public RoleMap() {
		roleMap.put(RoleConstant.ADMIN_NAME, RoleConstant.ADMIN);
		roleMap.put(RoleConstant.MERCHANT_NAME, RoleConstant.MERCHANT);
		roleMap.put(RoleConstant.EMPLOYEE_NAME, RoleConstant.EMPLOYEE);
		roleMap.put(RoleConstant.USER_NAME, RoleConstant.USER);
	}
}
