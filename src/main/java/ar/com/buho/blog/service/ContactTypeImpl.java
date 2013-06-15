package ar.com.buho.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import ar.com.buho.blog.model.ContactType;

@Service
public class ContactTypeImpl implements MailService {

	@Autowired(required=true)
	private ContactType contactType;
	
	@Autowired(required=true)
	private MailSender gmailMail;
	
	@Override
	public ContactType getContactType() {
		return this.contactType;
	}

	@Override
	public MailSender getMailSender() {
		return this.gmailMail;
	}

}
