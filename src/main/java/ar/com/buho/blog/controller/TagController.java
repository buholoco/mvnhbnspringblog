package ar.com.buho.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.model.Tag;
import ar.com.buho.blog.service.BlogService;

@Controller
@RequestMapping("/tag/{id}")
public class TagController {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String show(@PathVariable("id") long tagId,
			HttpServletRequest request, Model model) {
		logger.debug("Received request to get tag show");

		List<Post> listPost = new ArrayList<Post>();
		String page = request.getParameter("page");

		Tag tag = blogService.findTagById(tagId);
		listPost.addAll(tag.getPosts());
		
		PagedListHolder<Post> postList = new PagedListHolder<Post>(listPost,
				new MutableSortDefinition("created", true, false));
		postList.resort();
		postList.setPageSize(2);

		if (request.getParameter("cp") != null) {
			int currentPage = Integer.parseInt(request.getParameter("cp"));
			postList.setPage(currentPage);
		}
		

		if ("previous".equals(page)) {
			postList.previousPage();
		}
		if ("next".equals(page)) {
			postList.nextPage();
		}

		model.addAttribute("postList", postList);
		model.addAttribute("title", "Showing post for tag " + tag.getTitle());

		return "index";
	}
}
