package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.buho.blog.model.Comment;

@Service
public interface CommentService {

	public Comment findCommentbyId(long id);
	public List<Comment> findLastestComments();
	public void saveComment(Comment comment, long postId);
	public List<Comment> findComments();
	public void removeComment(long id);
}
