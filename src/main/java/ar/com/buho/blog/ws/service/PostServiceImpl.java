package ar.com.buho.blog.ws.service;

import javax.jws.WebMethod;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.BlogService;

public class PostServiceImpl implements PostService {

	@Autowired
	BlogService blogService;
	
	@WebMethod
	public Post findById(long id) {
		return blogService.findPostById(id);
	}

}
