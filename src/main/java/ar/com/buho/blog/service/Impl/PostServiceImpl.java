package ar.com.buho.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.generic.dao.IPostDAO;
import ar.com.buho.blog.generic.dao.ITagDAO;
import ar.com.buho.blog.model.Post;
import ar.com.buho.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired(required=true)
	private IPostDAO postDAO;
	
	@Autowired(required=true)
	private ITagDAO tagDAO;
	
	@Transactional(readOnly = true)
	public Post findPostById(long id) {
		return postDAO.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Post> findPostsByTagId(long id) {
		return (List<Post>) tagDAO.findById(id).getPosts();
	}
	
	@Transactional(readOnly=true) 
	public List<Post> findPostsByTitle(String title) {
		return (List<Post>) postDAO.findByTitle(title);
	}
	
	@Transactional(readOnly = false)
	public void savePost(Post post) {
		postDAO.create(post);
	}
	
	@Transactional(readOnly = false)
	public void updatePost(Post post) {
		postDAO.update(post);
	}

	@Transactional(readOnly = true)
	public List<Post> findPosts() {
		return postDAO.findAll();
	}

	@Transactional(readOnly = false)
	public void removePost(long id) {
		postDAO.deleteById(id);
		
	}
}
