package ar.com.buho.blog.generic.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.buho.blog.generic.dao.IOperations;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDAO<T extends Serializable> implements IOperations<T>{
	protected static Logger logger = Logger.getLogger("dao");
	private Class<T> clazz;

	@Autowired(required=true)
	private SessionFactory sessionFactory;

	protected final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
	
	public final T findById(long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public final List<T> findAll() {
		return getCurrentSession().createCriteria(clazz.getName())
				.list();
	}
	
	public final List<T> findAll(HashMap<String, String> options) {
		Criteria criteria = getCurrentSession().createCriteria(clazz.getName());
		
		if (options.containsKey("orderBy")) {
			criteria.addOrder(Order.desc(options.get("orderBy")));
		}
		
		if (options.containsKey("maxResults")) {
			criteria.setMaxResults(Integer.parseInt(options.get("maxResults")));
		}
		
		if (options.containsKey("offset")) {
			criteria.setFirstResult(Integer.parseInt(options.get("offset")));
		}
		return criteria.list();
	}

	public void create(T entity) {
		logger.debug(entity.getClass() + " - create (T entity)");
		getCurrentSession().persist(entity);
	}

	public T update(T entity) {
		logger.debug(entity.getClass() + " - update (T entity)");
		return (T) getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		logger.debug(entity.getClass() + " - delete (T entity)");
		getCurrentSession().delete(entity);
	}

	public final void deleteById(long entityId) {
		T entity = findById(entityId);
		logger.debug(entity.getClass() + " - deleteById (long entityId)");
		getCurrentSession().delete(entity);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
