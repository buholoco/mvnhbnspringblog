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
	public boolean onSave(Object entity, Serializable id, Object[] currentState,
			String[] propertyNames, Type[] types) {
		logger.debug("Intercept onSave Timestampable object");
		
		if ((entity instanceof Timestampable)) {
			int createdIndex = (Arrays.asList(propertyNames).indexOf("created"));
			currentState[createdIndex] = new Date();
			
			int updatedIndex = (Arrays.asList(propertyNames).indexOf("updated"));
			currentState[updatedIndex] = new Date();
			return true;
		}
		for (String property: propertyNames) {
			System.out.println(property);
		}
		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		logger.debug("Intercept onFlushDirty Timestampable object");
		
		if ((entity instanceof Timestampable)) {
			
			for (int i = 0; i < propertyNames.length; i++) {
				if ("updated".equals(propertyNames[i]))
					currentState[i] = new Date();
			}
			return true;
		}
		
		return false;
	}

}
