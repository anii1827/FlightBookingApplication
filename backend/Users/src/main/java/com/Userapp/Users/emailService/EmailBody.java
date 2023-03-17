package com.Userapp.Users.emailService;

public class EmailBody {
			public String recipientEmail[];
			public String message;
			public String subject;
			
			public String getSubject() {
				return subject;
			}
			public void setSubject(String subject) {
				this.subject = subject;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public String[] getRecipientEmail() {
				return recipientEmail;
			}
			public void setRecipientEmail(String[] recipientEmail) {
				this.recipientEmail = recipientEmail;
			}
			public EmailBody(String[] recipientEmail, String message, String subject) {
				this.recipientEmail = recipientEmail;
				this.message = message;
				this.subject = subject;
			}			
}
