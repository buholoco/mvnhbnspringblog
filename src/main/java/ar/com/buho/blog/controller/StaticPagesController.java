package ar.com.buho.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.ContactType;
import ar.com.buho.blog.service.MailService;

@Controller
@SessionAttributes("contactType")
public class StaticPagesController {
	

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired(required=true)
	private MailService mailService;
	
	@Autowired(required=true)
	private String defaultTo;
	
	@RequestMapping(value = "/contact.htm", method = RequestMethod.GET)
	public String showContactForm(Model model) {
		logger.debug("Received request to get showContactForm");

		model.addAttribute("title", "Contact Us");
		model.addAttribute(new ContactType());
		
		return "contact";
	}

	@RequestMapping(value = "/contact.htm", method = RequestMethod.POST)
	public String processContactForm(@ModelAttribute("contactType") @Valid ContactType contactType,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, SessionStatus status, HttpServletRequest request) {
		logger.debug("Received request to post processContactForm " + contactType);
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Contact Us");
			return "contact";
		} else {
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(contactType.getFrom());
			mailMessage.setTo(defaultTo);
			mailMessage.setSubject(contactType.getSubject());
			mailMessage.setText(contactType.getText());
			MailSender mailSender =  mailService.getMailSender();
			mailSender.send(mailMessage);
			redirectAttributes.addFlashAttribute("success", "Contact Mail Sent!");
	
			return "redirect:/";
		}
	}
}
