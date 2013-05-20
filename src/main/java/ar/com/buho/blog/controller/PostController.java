package ar.com.buho.blog.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.BlogService;

@Controller
public class PostController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private BlogService blogService;

	@RequestMapping("/")
	public String listContacts(Model model) {
		logger.debug("Received request to get /");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String name = auth.getName();
			
			model.addAttribute("username", name);
		}
		
		model.addAttribute("title", "Recent posts");
		model.addAttribute("postList", blogService.findPosts());

		return "index";
	}

	@RequestMapping("/post/show/{id}")
	public String showPost(@PathVariable Integer id, Model model) {
		logger.debug("Received request to show post");

		Post post = blogService.findPostById(id);
		model.addAttribute("title", "Show " + post.getTitle());
		model.addAttribute("post", post);

		return "post-show";
	}

	@RequestMapping(value = "/post/add", method = RequestMethod.GET)
	public String addPost(Model model) {
		logger.debug("Received request to get post-add");

		model.addAttribute("title", "New Post");
		model.addAttribute("post", new Post());

		return "post-add";
	}

	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("post") @Valid Post post,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		logger.debug("Received request to post post-add");

		if (result.hasErrors()) {
			model.addAttribute("title", "New/Edit Post");
			return "post-add";
		}

		blogService.savePost(post);
		redirectAttributes.addFlashAttribute("success", "Post saved!");

		return "redirect:/";
	}

	@RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
	public String editPost(Model model, @PathVariable Integer id) {
		logger.debug("Received request to get edit-post");

		model.addAttribute("title", "Edit Post");
		Post post = blogService.findPostById(id);
		model.addAttribute("post", post);

		return "post-add";
	}

	@RequestMapping(value = "/post/delete/{id}", method = RequestMethod.GET)
	public String deletePost(Model model, @PathVariable Integer id) {
		logger.debug("Received request to delete post");

		blogService.removePost(id);

		return "redirect:/";
	}
}
