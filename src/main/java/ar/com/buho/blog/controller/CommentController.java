package ar.com.buho.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.service.BlogService;
import ar.com.buho.blog.service.JsonResponse;

@Controller
@RequestMapping("/post/{idPost}")
public class CommentController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/comment/add.htm", method = RequestMethod.GET)
	public String addComment(@PathVariable("id") long postId, Model model) {
		logger.debug("Received request to get comment-add");
		
		model.addAttribute(blogService.findPostById(postId));
		model.addAttribute("title", "Add comment");
		model.addAttribute("comment", new Comment());
		
		return "comment-add";
	}
	
	@RequestMapping(value = "/comment/add.htm", method = RequestMethod.POST)
	public String processForm(
			@ModelAttribute("comment") @Valid Comment comment, BindingResult result, 
			@PathVariable("idPost") long postId, RedirectAttributes redirectAttributes, 
			Model model, SessionStatus status, HttpServletRequest request) {
		logger.debug("Received request post to add new comment" + comment.toString());
		
		if (result.hasErrors()) {
			model.addAttribute("title", "New/Edit Comment");
			return "comment-add";
		} else {
			blogService.saveComment(comment, postId);
			
			status.setComplete();
			request.getSession().removeAttribute("postList");
			redirectAttributes.addFlashAttribute("success","Comment saved!");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/comment/add.ajax", method = RequestMethod.POST) 
	public @ResponseBody JsonResponse addComment(@ModelAttribute("comment") @Valid Comment comment, 
			BindingResult result, @PathVariable("idPost") long postId, SessionStatus status,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Received request post to add new comment ajax" + comment.toString());
		
		JsonResponse res = new JsonResponse();

		if (result.hasErrors()) {
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		} else {
			blogService.saveComment(comment, postId);
			status.setComplete();
			request.getSession().removeAttribute("postList");
			res.setStatus("SUCCESS");
			res.setResult(comment);
		}
		return res;
	}

	
}
