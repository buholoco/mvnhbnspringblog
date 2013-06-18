package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.buho.blog.model.Post;

@Service
public interface PostService {

	public Post findPostById(long id);
	public List<Post> findPostsByTitle(String title);
	public List<Post> findPostsByTagId(long id);
	public void savePost(Post post);
	public void updatePost(Post post);
	public List<Post> findPosts();
	public void removePost(long id);
}
