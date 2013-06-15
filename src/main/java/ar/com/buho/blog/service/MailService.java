package ar.com.buho.blog.service;

import org.springframework.mail.MailSender;

import ar.com.buho.blog.model.ContactType;

public interface MailService {

	public ContactType getContactType();
	
	public MailSender getMailSender();

}
