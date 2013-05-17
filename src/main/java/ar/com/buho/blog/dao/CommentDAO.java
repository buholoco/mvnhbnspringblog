package ar.com.buho.blog.dao;

import java.util.List;

import ar.com.buho.blog.model.Comment;

public interface CommentDAO {

	public Comment findById(int id);
	public void save(Comment comment, Integer postId);
	public List<Comment> findComments();
	public void remove(int id);
}