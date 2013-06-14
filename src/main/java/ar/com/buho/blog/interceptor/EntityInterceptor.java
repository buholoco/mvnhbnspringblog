package ar.com.buho.blog.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import ar.com.buho.blog.model.Timestampable;

public class EntityInterceptor extends EmptyInterceptor {

	protected static Logger logger = Logger.getLogger("interceptor");

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867083174286346616L;

	@Override
	public boolean onSave(Object entity, Serializable id,
			Object[] currentState, String[] propertyNames, Type[] types) {
		logger.debug("Intercept onSave object " + entity.getClass());

		if (entity instanceof Timestampable) {
			int createdIndex = (Arrays.asList(propertyNames).indexOf("created"));
			currentState[createdIndex] = new Date();

			int updatedIndex = (Arrays.asList(propertyNames).indexOf("updated"));
			currentState[updatedIndex] = new Date();
			return true;
		}

		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		logger.debug("Intercept onFlushDirty object " + entity.getClass());

		if (entity instanceof Timestampable) {

			int updatedIndex = (Arrays.asList(propertyNames).indexOf("updated"));
			currentState[updatedIndex] = new Date();

			return true;
		}

		return false;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		logger.debug("Intercept onDelete object " + entity.getClass());
	}

	@Override
	public void onCollectionUpdate(Object collection, Serializable key) {
		logger.debug("Intercept onCollectionUpdate " + collection.getClass()
				+ collection);
		
	}

	@Override
	public void onCollectionRecreate(Object collection, Serializable key) {
		logger.debug("Intercept onCollectionRecreate" + collection.getClass()
				+ collection);

	}

	@Override
	public void onCollectionRemove(Object collection, Serializable key) {
		logger.debug("Intercept onCollectionRemove" + collection);

	}

}
