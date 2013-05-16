package ar.com.buho.blog.service;

import java.util.List;

import ar.com.buho.blog.model.Post;

public interface PostService {
	
	public Post getPost(int id);
	public void savePost(Post post);
	public List<Post> listPost();
	public void removePost(int id);

}
