package ar.com.buho.blog.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.buho.blog.model.Comment;
import ar.com.buho.blog.model.Post;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Comment findById(int id) {
		return (Comment) sessionFactory.getCurrentSession().get(Comment.class, id);
	}

	@Override
	public void save(Comment comment, Integer postId) {
		Post post = (Post) sessionFactory.getCurrentSession().get(Post.class, postId);
		
		comment.setPost(post);
		
		sessionFactory.getCurrentSession().save(comment);
		
		post.getComments().add(comment);
		
		sessionFactory.getCurrentSession().saveOrUpdate(post);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findComments() {
		return (List<Comment>) sessionFactory.getCurrentSession().createQuery("from Comment c").list();
	}

	@Override
	public void remove(int id) {
		Comment comment = findById(id);
		sessionFactory.getCurrentSession().delete(comment);
	}

}
