package ar.com.buho.blog.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String listContacts(Map<String, Object> map) {

		map.put("title", "Home Page");
		map.put("postList", postService.listPost());
		

		return "index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPost(Model model) {
		model.addAttribute("title", "title");
		model.addAttribute("post", new Post());
		return "add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(
			@ModelAttribute("post")	@Valid Post post, 
			BindingResult result) {

		if (result.hasErrors()) {
			return "add";
		}
			
		postService.addPost(post);

		return "redirect:/";
	}
}
