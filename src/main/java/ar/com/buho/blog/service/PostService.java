package ar.com.buho.blog.service;

import java.util.List;

import ar.com.buho.blog.model.Post;

public interface PostService {
	
	public void addPost(Post post);
	public List<Post> listPost();
	public void removePost(Integer id);

}
