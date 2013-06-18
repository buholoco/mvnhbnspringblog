package ar.com.buho.blog.ws.service;

import javax.jws.WebMethod;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.buho.blog.model.Post;

import ar.com.buho.blog.service.PostService;

public class PostServiceImpl implements ar.com.buho.blog.ws.service.PostService {

	@Autowired
	private PostService postService;
	
	@WebMethod
	public Post findById(long id) {
		return postService.findPostById(id);
	}

}