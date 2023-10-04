package com.parking.service;

import javax.mail.MessagingException;

public interface EmailSenderService {
	void sendVerificationEmail(String toEmail, String verificationCode) throws MessagingException ;
}
