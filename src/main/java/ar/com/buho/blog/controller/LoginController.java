package ar.com.buho.blog.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String doLogin(Model model) {
		logger.debug("Received request to get /login");
		
		model.addAttribute("title", "Blog login");
		return "login";
	}
	
	@RequestMapping(value = "/loginfailed.htm", method = RequestMethod.GET)
	public String loginerror(Model model) {
		logger.debug("Received request to get /loginfailed");
 
		model.addAttribute("title", "Blog login");
		model.addAttribute("error", "true");
		return "login";
 
	}

}
