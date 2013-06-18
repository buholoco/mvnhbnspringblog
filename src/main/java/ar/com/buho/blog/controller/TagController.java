package ar.com.buho.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.model.Tag;
import ar.com.buho.blog.service.BlogService;
import ar.com.buho.blog.service.TagService;

@Controller
public class TagController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") long tagId,
			HttpServletRequest request, Model model) {
		logger.debug("Received request to get tag show");

		Tag tag = tagService.findTagById(tagId);
		List<Post> listPost = new ArrayList<Post>();
				
		listPost.addAll(tag.getPosts());
		PagedListHolder<Post> postList = blogService.getPagedList(listPost, "created", false);
		
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		postList.setPage(page);
		
		model.addAttribute("postList", postList);
		model.addAttribute("title", "Showing post for tag " + tag.getTitle());

		return "index";
	}
}
