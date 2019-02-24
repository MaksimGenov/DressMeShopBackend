package com.genov.dress_me_shop.utils;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailSender {
	void send(String reciver, String subject, String text) throws AddressException, MessagingException;
}
