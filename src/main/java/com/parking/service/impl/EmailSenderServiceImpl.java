package com.parking.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.parking.constant.SessionConstant;
import com.parking.service.EmailSenderService;
import com.parking.utils.TimeFormat;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendVerificationEmail(String toEmail, String username, String verificationCode)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("your-email@gmail.com");
		helper.setTo(toEmail);
		helper.setSubject("Xác thực tài khoản Convenient Parking");

		TimeFormat time = new TimeFormat(SessionConstant.OTP_EXPIRE_TIME);

		String htmlBody = "<div>\r\n" + "    <h1 style=\"color: darkcyan;\">Mã đặt xác thực tài khoản</h1>\r\n"
				+ "    <p>Xin dùng mã này để xác thực cho tài khoản Convenient Parking " + username + " .</p>\r\n"
				+ "    <p>Đây là mã của bạn: <strong> " + verificationCode + "</strong></p>\r\n"
				+ "    <p>Nếu bạn không sử dụng liên kết này trong vòng " + time.toString()
				+ ", nó sẽ hết hạn. Để nhận liên kết đặt lại mật khẩu mới, hãy\r\n" + "        truy cập: </p>\r\n"
				+ "    <p>Xin cám ơn,</p>\r\n" + "    <p>Nhóm tài khoản Convenient Parking</p>\r\n" + "</div>";

		helper.setText(htmlBody, true); 

		mailSender.send(message);
	}
}
