package ar.com.buho.blog.generic.dao;

import org.springframework.stereotype.Repository;

import ar.com.buho.blog.model.Post;

@Repository
public class PostDAO extends AbstractHibernateDAO<Post> implements IPostDAO {

	public PostDAO() {
		setClazz(Post.class);
	}

}
