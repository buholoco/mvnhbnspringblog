package ar.com.buho.blog.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.buho.blog.model.Post;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Post findById(int id) {
		return (Post) sessionFactory.getCurrentSession().get(Post.class, id);
	}
	
	@Override
	public void save(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}
	
	@Override
	public void update(Post post) {
		sessionFactory.getCurrentSession().update(post);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> findPosts() {
		return (List<Post>) sessionFactory.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public void remove(int id) {
		Post post = findById(id);
		sessionFactory.getCurrentSession().delete(post);

	}

}
