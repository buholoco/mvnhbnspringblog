package ar.com.buho.blog.generic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.buho.blog.generic.dao.IPostDAO;
import ar.com.buho.blog.model.Post;

@Repository
public class PostDAO extends AbstractHibernateDAO<Post> implements IPostDAO {

	public PostDAO() {
		setClazz(Post.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> findByTitle(String title) {
		List<Post> posts = getCurrentSession().createQuery("from Post p where p.title like :post_title")
				.setParameter("post_title", title + "%")
				.list();
		
		return posts;
	}
	
}