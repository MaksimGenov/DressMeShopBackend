package com.genov.dress_me_shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class MailSenderImpl implements MailSender {
	private final String sender = "maximtest@abv.bg";
	private final String password = "As123456";
	private Properties props;

	public MailSenderImpl() {
		this.setProps();
	}

	private void setProps() {
		this.props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "smtp.abv.bg");
		props.put("mail.smtp.port", "465");
	}

	@Override
	public void send(String reciver, String subject, String text) throws AddressException, MessagingException {
			Message message = new MimeMessage(this.getSession());
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciver));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.print("sent");

	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};
		
		return Session.getInstance(props, authenticator);
	}
}
