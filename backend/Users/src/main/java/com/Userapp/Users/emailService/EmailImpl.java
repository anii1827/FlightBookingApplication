package com.Userapp.Users.emailService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.Userapp.Users.Exception.CustomException;

@Service
public class EmailImpl implements Email{

	@Autowired
	private JavaMailSender mailSender;
	
	 @Value("${from.email.address}")
	 private String fromEmailAddress;
	
	@Override
	public void sendEmail(EmailBody body) {
		SimpleMailMessage mail = new SimpleMailMessage(); 
		try {
		mail.setFrom(fromEmailAddress);
		mail.setTo(body.getRecipientEmail());
		mail.setText(body.getMessage());
		mail.setSentDate(Date.valueOf(LocalDate.now()));
		mail.setSubject(body.getSubject());
		mailSender.send(mail);
		}
		catch(MailParseException e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(MailAuthenticationException e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(MailSendException e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(MailException e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	
	
}
