package com.employee_management_system.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		
		this.javaMailSender = javaMailSender;
	}
	public void sendOtp(String toEmail,String otp) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject("otp verification");
		mailMessage.setText("your otp is"+ otp);
		javaMailSender.send(mailMessage);
		
		
	}
	

}
