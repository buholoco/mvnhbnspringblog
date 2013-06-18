package ar.com.buho.blog.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ar.com.buho.blog.model.Post;

@WebService
public interface PostService {
	
	@WebMethod
	public Post findById(long id);

}
