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
	public void savePost(Post post) {
		postDAO.savePost(post);
		
	}

	@Transactional
	@Override
	public List<Post> listPost() {
		return postDAO.listPost();
	}

	@Transactional
	@Override
	public void removePost(int id) {
		postDAO.removePost(id);
		
	}

	@Transactional
	@Override
	public Post getPost(int id) {
		return postDAO.getPost(id);
	}

}
