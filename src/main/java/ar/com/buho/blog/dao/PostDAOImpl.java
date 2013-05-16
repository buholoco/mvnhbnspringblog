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
	public void savePost(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	@Override
	public List<Post> listPost() {
		return (List<Post>) sessionFactory.getCurrentSession().createQuery("from Post").list();
	}

	@Override
	public void removePost(int id) {
		Post post = (Post) sessionFactory.getCurrentSession().get(Post.class, id);
		sessionFactory.getCurrentSession().delete(post);

	}

	@Override
	public Post getPost(int id) {
		return (Post) sessionFactory.getCurrentSession().get(Post.class, id);
	}


}
