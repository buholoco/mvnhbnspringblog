package ar.com.buho.blog.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.propertyeditors.CommaDelimitedStringEditor;
import ar.com.buho.blog.service.BlogService;

@Controller
@SessionAttributes("post")
public class PostController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired(required = true)
	private BlogService blogService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "tags",
				new CommaDelimitedStringEditor(blogService));
	}

	@RequestMapping("post/{id}")
	@ResponseBody
	public Post getPostById(@PathVariable long id) {
		logger.debug("Received request to get PostById (json)");

		return blogService.findPostById(id);
	}

	@RequestMapping("/")
	public String listPosts(HttpServletRequest request, Model model) {
		logger.debug("Received request to get /");

		List<Post> listPost = blogService.findPosts();

		PagedListHolder<Post> pagedPostList = blogService.getPagedList(
				listPost, "created", false);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedPostList.setPage(page);

		model.addAttribute("title", "Recent posts");
		model.addAttribute("postList", pagedPostList);

		return "index";
	}

	@RequestMapping("/post/{id}/show.htm")
	public String showPost(@PathVariable long id, Model model) {
		logger.debug("Received request to show post");

		Post post = blogService.findPostById(id);
		model.addAttribute("title", "Show " + post.getTitle());
		model.addAttribute("newComment", new Comment());
		model.addAttribute(post);

		return "post-show";
	}

	@RequestMapping(value = "/search.htm", method = RequestMethod.GET)
	public String searchPosts(
			@RequestParam(value = "title", required = false) String title,
			HttpServletRequest request, Model model) {
		logger.debug("Received request to post /search.htm, search " + title);

		if (title == null) {
			model.addAttribute("title", "Search");

			return "search";
		} else {
			List<Post> listPost = blogService.findPostsByTitle(title);
			logger.debug("Received request to post /search.htm, return  "
					+ listPost);
			PagedListHolder<Post> pagedPostList = blogService.getPagedList(
					listPost, "created", false);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedPostList.setPage(page);

			model.addAttribute("title", "Showing results for posts with title "
					+ title);
			model.addAttribute("searchString", title);
			model.addAttribute("postList", pagedPostList);

			return "index";
		}
	}

	@RequestMapping(value = "/post/add.htm", method = RequestMethod.GET)
	public String addPost(Model model) {
		logger.debug("Received request to get post-add");

		model.addAttribute("title", "New Post");
		model.addAttribute(new Post());

		return "post-add";
	}

	@RequestMapping(value = "/post/add.htm", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("post") @Valid Post post,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, SessionStatus status, HttpServletRequest request) {
		logger.debug("Received request to post post-add" + post);

		if (result.hasErrors()) {
			model.addAttribute("title", "New/Edit Post");
			return "post-add";
		} else {
			if (post.getId() > 0) {
				blogService.updatePost(post);
			} else {
				blogService.savePost(post);
			}
			status.setComplete();
			redirectAttributes.addFlashAttribute("success", "Post saved!");

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/post/{id}/edit.htm", method = RequestMethod.GET)
	public String editPost(Model model, @PathVariable long id) {
		logger.debug("Received request to get edit-post");

		model.addAttribute("title", "Edit Post");
		Post post = blogService.findPostById(id);
		model.addAttribute(post);

		return "post-add";
	}

	@RequestMapping(value = "/post/{id}/delete.htm", method = RequestMethod.GET)
	public String deletePost(Model model, @PathVariable long id,
			SessionStatus status, HttpServletRequest request) {
		logger.debug("Received request to delete post");

		blogService.removePost(id);
		status.setComplete();

		return "redirect:/";
	}

}
