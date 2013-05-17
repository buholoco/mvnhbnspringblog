package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.dao.CommentDAO;
import ar.com.buho.blog.dao.PostDAO;
import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Transactional(readOnly = true)
	@Override
	public Post findPostById(int id) {
		return postDAO.findById(id);
	}
	
	@Transactional
	@Override
	public void savePost(Post post) {
		postDAO.save(post);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Post> findPosts() {
		return postDAO.findPosts();
	}

	@Transactional
	@Override
	public void removePost(int id) {
		postDAO.remove(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Comment findCommentbyId(int id) {
		return commentDAO.findById(id);
	}

	@Transactional
	@Override
	public void saveComment(Comment comment, Integer postId) {
		commentDAO.save(comment, postId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Comment> findComments() {
		return (List<Comment>) commentDAO.findComments();
	}
	
	@Transactional
	@Override
	public void removeComment(int id) {
		commentDAO.remove(id);
	}

}
