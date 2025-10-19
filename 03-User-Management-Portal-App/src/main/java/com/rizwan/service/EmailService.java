package com.rizwan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailsender;
	public boolean sendEmail(String subject, String body, String to) {
		
		try {
			MimeMessage mimeMessage = mailsender.createMimeMessage();
			MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage);
			helper.setSubject(subject);
			helper.setText(body);
			helper.setTo(to);
			mailsender.send(mimeMessage);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

}
