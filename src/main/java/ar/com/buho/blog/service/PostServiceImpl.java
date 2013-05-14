package ar.com.buho.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.buho.blog.dao.PostDAO;
import ar.com.buho.blog.model.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;
	
	@Transactional
	@Override
	public void addPost(Post post) {
		postDAO.addPost(post);
		
	}

	@Transactional
	@Override
	public List<Post> listPost() {
		return postDAO.listPost();
	}

	@Transactional
	@Override
	public void removePost(Integer id) {
		postDAO.removePost(id);
		
	}

}
