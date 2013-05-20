package ar.com.buho.blog.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.service.BlogService;

@Controller
@RequestMapping("/post/{id}")
public class CommentController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/comment/add", method = RequestMethod.GET)
	public String addComment(@PathVariable("id") Integer id, Model model) {
		logger.debug("Received request to get comment-add");
		
		model.addAttribute("post", blogService.findPostById(id));
		model.addAttribute("title", "Add comment");
		model.addAttribute("comment", new Comment());
		
		return "comment-add";
	}
	
	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public String processForm(
			@ModelAttribute("comment") @Valid Comment comment, 
			BindingResult result,
			@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes,
			Model model) {
		logger.debug("Received request post to add new comment");
		
		if (result.hasErrors()) {
			model.addAttribute("title", "New/Edit Comment");
			return "comment-add";
		}
		blogService.saveComment(comment, id);
		
//		post.getComments().add(comment);
//		blogService.savePost(post);
		
		
		redirectAttributes.addFlashAttribute("success","Comment saved!");
		return "redirect:/";
	}

	
}
