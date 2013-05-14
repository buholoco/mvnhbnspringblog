package ar.com.buho.blog.dao;

import java.util.List;

import ar.com.buho.blog.model.Post;

public interface PostDAO {

	public void addPost(Post post);
	public List<Post> listPost();
	public void removePost(Integer id);
}
