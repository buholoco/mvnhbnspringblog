package ar.com.buho.blog.service;

import java.util.List;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;

public interface BlogService {
	
	public Post findPostById(int id);
	public void savePost(Post post);
	public List<Post> findPosts();
	public void removePost(int id);
	public Comment findCommentbyId(int id);
	public void saveComment(Comment comment, Integer postId);
	public List<Comment> findComments();
	public void removeComment(int id);

}
