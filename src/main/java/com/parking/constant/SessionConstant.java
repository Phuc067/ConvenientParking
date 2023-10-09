package com.parking.constant;

public class SessionConstant {
	public static final String CURRENT_USER ="currentUser"; 
	public static final String CURRENT_ROLE ="currentRole";
	public static final String CURRENT_OTP ="currentOTP";
	public static final Long OTP_EXPIRE_TIME = 1800L;
	public static final Long REFRESH_TOKEN_EXPRIRY_TIME = 300L;
	private SessionConstant() {}
}
