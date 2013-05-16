package ar.com.buho.blog.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.PostService;

@Controller
@SessionAttributes("post")
public class HomeController {

	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String listContacts(Model model) {

		model.addAttribute("page_title", "Home Page");
		model.addAttribute("postList", postService.listPost());
		

		return "index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPost(Model model) {
		model.addAttribute("title", "New Post");
		model.addAttribute("post", new Post());
		return "add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processForm(
			@ModelAttribute("post")	@Valid Post post, 
			BindingResult result) {

		if (result.hasErrors()) {
			return "add";
		}
			
		postService.savePost(post);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/edit/post/{id}", method = RequestMethod.PUT)
	public String editPost(
			Model model, 
			@PathVariable Integer id
			) 
	{
		model.addAttribute("title", "Edit Post");
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		return "/add";
	}
	
	@RequestMapping(value = "/delete/post/{id}", method = RequestMethod.DELETE)
	public String deletePost(
			Model model,
			@PathVariable Integer id
			)
	{
		postService.removePost(id);
		return "redirect:/";
	}
}
