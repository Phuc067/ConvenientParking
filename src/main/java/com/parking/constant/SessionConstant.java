package com.parking.constant;

public class SessionConstant {
	public static final String CURRENT_USER ="currentUser"; 
	public static final String CURRENT_ROLE ="currentRole";
	public static final String CURRENT_OTP ="currentOTP";
	public static Long verification_time =0L;
	public static final Long OTP_EXPIRE_TIME = 300L;
	public static final Long ACCESS_TOKEN_EXPRIRY_TIME = 60L;
	public static final Long REFRESH_TOKEN_EXPRIRY_TIME = 10080L;
	private SessionConstant() {}
}
