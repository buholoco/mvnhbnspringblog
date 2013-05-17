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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.BlogService;

@Controller
@SessionAttributes("post")
public class HomeController {

	@Autowired
	private BlogService blogService;

	@RequestMapping("/")
	public String listContacts(Model model) {

		model.addAttribute("title", "Recent posts");
		model.addAttribute("postList", blogService.findPosts());
		

		return "index";
	}
	
	@RequestMapping("post/show/{id}")
	public String showPost(
			@PathVariable Integer id, 
			Model model) {
		Post post = blogService.findPostById(id);
		model.addAttribute("post", post);
		return "post-show";
	}
	
	@RequestMapping(value = "/post/add", method = RequestMethod.GET)
	public String addPost(Model model) {
		model.addAttribute("title", "New Post");
		model.addAttribute("post", new Post());
		return "post-add";
	}
	
	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String processForm(
			@ModelAttribute("post")	@Valid Post post, 
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model) 
		{

		if (result.hasErrors()) {
			model.addAttribute("title", "New/Edit Post");
			return "post-add";
		}
			
		blogService.savePost(post);
		redirectAttributes.addFlashAttribute("success","Post saved!");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
	public String editPost(
			Model model, 
			@PathVariable Integer id
			) {
		model.addAttribute("title", "Edit Post");
		Post post = blogService.findPostById(id);
		model.addAttribute("post", post);
		return "post-add";
	}
	
	@RequestMapping(value = "/post/delete/{id}", method = RequestMethod.GET)
	public String deletePost(
			Model model,
			@PathVariable Integer id
			) {
		blogService.removePost(id);
		return "redirect:/";
	}
}
