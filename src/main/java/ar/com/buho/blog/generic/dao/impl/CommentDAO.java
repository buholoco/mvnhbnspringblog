package ar.com.buho.blog.generic.dao.impl;

import org.springframework.stereotype.Repository;

import ar.com.buho.blog.generic.dao.ICommentDAO;
import ar.com.buho.blog.model.Comment;

@Repository
public class CommentDAO extends AbstractHibernateDAO<Comment> implements ICommentDAO {
	public CommentDAO() {
		setClazz(Comment.class);
	}
	
	
}
