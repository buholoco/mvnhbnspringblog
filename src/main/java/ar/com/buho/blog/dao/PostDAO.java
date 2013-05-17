package ar.com.buho.blog.dao;

import java.util.List;

import ar.com.buho.blog.model.Post;

public interface PostDAO {

	public Post findById(int id);
	public void save(Post post);
	public List<Post> findPosts();
	public void remove(int id);
}
