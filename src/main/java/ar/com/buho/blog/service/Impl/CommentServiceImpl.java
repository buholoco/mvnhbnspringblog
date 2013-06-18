package ar.com.buho.blog.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.generic.dao.ICommentDAO;
import ar.com.buho.blog.generic.dao.IPostDAO;
import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired(required=true)
	private IPostDAO postDAO;
	
	@Autowired(required=true)
	private ICommentDAO commentDAO;
	
	@Transactional(readOnly = true)
	public Comment findCommentbyId(long id) {
		return commentDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Comment> findLastestComments() {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("orderBy", "created");
		options.put("maxResults", "5");
		return commentDAO.findAll(options);
	}

	@Transactional(readOnly = false)
	public void saveComment(Comment comment, long postId) {
		Post post = postDAO.findById(postId);
		comment.setPost(post);
		commentDAO.create(comment);     		
    }

	@Transactional(readOnly = true)
	public List<Comment> findComments() {
		return commentDAO.findAll();
	}
	
	@Transactional(readOnly = false)
	public void removeComment(long id) {
		commentDAO.deleteById(id);
	}

}
