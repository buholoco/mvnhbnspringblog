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
	public void addPost(Post post) {
		sessionFactory.getCurrentSession().save(post);
	}

	@Override
	public List<Post> listPost() {
		return (List<Post>) sessionFactory.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public void removePost(Integer id) {
		sessionFactory.getCurrentSession().delete(id);

	}

}
