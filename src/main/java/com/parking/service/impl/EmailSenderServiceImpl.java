package com.parking.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.parking.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendVerificationEmail(String toEmail, String verificationCode) throws MessagingException {
//		SimpleMailMessage  message = new SimpleMailMessage();
//		message.setFrom("security.convenient-parking@gmail.com");
//		message.setTo(toEmail);
//		String body = "Mã đặt xác thực tài khoản\r\n"
//				+ "Xin dùng mã này để xác thực cho tài khoản Convenient Parking " +toEmail +".\r\n"
//				+ "Đây là mã của bạn: " +verificationCode + "\r\n"
//				+ "Xin cám ơn,\r\n";
//		message.setText(body);
//		mailSender.send(message);
//		System.out.print("Mail send successfully");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("your-email@gmail.com");
		helper.setTo(toEmail);
		helper.setSubject("Xác thực tài khoản Convenient Parking");

		// Định dạng email dưới dạng HTML
		String htmlBody =
				"<div>\r\n"
				+ "    <h1 style=\"color: darkcyan;\">Mã đặt xác thực tài khoản</h1>\r\n"
				+ "    <p>Xin dùng mã này để xác thực cho tài khoản Convenient Parking " + toEmail + " .</p>\r\n"
				+ "    <p>Đây là mã của bạn: <strong> " + verificationCode + "</strong></p>\r\n"
				+ "    <p>Nếu bạn không sử dụng liên kết này trong vòng 3 giờ, nó sẽ hết hạn. Để nhận liên kết đặt lại mật khẩu mới, hãy\r\n"
				+ "        truy cập: </p>\r\n"
				+ "    <p>Xin cám ơn,</p>\r\n"
				+ "    <p>Nhóm tài khoản Convenient Parking</p>\r\n"
				+ "</div>";

		helper.setText(htmlBody, true); // Đặt email dưới dạng HTML

		mailSender.send(message);
		System.out.print("Mail send successfully");
	}
}
