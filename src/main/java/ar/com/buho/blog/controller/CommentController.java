package ar.com.buho.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.buho.blog.service.BlogService;

@Controller
@SessionAttributes("comment")
@RequestMapping(value = "/blog/{id}")
public class CommentController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/comment/add", method = RequestMethod.GET)
	public String addComment(Model model) {
		return "comment-add";
	}

	
}
