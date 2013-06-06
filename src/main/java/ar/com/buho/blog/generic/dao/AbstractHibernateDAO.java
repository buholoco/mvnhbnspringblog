package ar.com.buho.blog.generic.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T extends Serializable> implements IOperations<T>{
	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	protected final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public final T findById(long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	public void create(T entity) {
		getCurrentSession().persist(entity);
	}

	public final T update(T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	public final void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public final void deleteById(long entityId) {
		T entity = findById(entityId);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
